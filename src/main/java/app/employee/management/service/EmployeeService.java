package app.employee.management.service;

import app.employee.management.model.Employee;
import app.employee.management.repository.entity.EmployeeEntity;
import app.employee.management.repository.entity.PhoneNumberEntity;
import app.employee.management.repository.enums.OrderEnum;
import app.employee.management.repository.enums.SexEnum;
import app.employee.management.repository.jpa.EmployeeJpaRepository;
import app.employee.management.repository.mapper.EmployeeMapper;
import app.employee.management.utils.exception.NotFoundException;
import app.employee.management.view.model.EmployeeDatas;
import com.opencsv.CSVWriter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;

import static app.employee.management.view.mapper.EmployeeViewMapper.fromStringToInstant;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {
  private final EmployeeJpaRepository repository;
  private final EmployeeMapper mapper;
  private final EntityManager entityManager;

  private static Predicate[] retrieveNotNullPredicates(String lastName, String firstName,
                                                       SexEnum sex, String function,
                                                       Instant hiredFrom, Instant hiredTo,
                                                       Instant wentFrom, Instant wentTo,
                                                       CriteriaBuilder builder,
                                                       Root<EmployeeEntity> root,
                                                       List<Predicate> predicates,
                                                       String phoneCode, Join<EmployeeEntity,
      PhoneNumberEntity> join) {
    ZoneId zoneId = ZoneId.systemDefault();
    if (firstName != null) {
      predicates.add(builder.or(
          builder.like(root.get("firstName"), "%" + firstName + "%"),
          builder.like(builder.lower(root.get("firstName")), "%" + firstName + "%")
      ));
    }
    if (lastName != null) {
      predicates.add(builder.or(
          builder.like(root.get("lastName"), "%" + lastName + "%"),
          builder.like(builder.lower(root.get("lastName")), "%" + lastName + "%")
      ));
    }
    if (phoneCode != null) {
      predicates.add(builder.or(
          builder.like(join.get("code"), "%" + phoneCode + "%"),
          builder.like(builder.lower(join.get("code")), "%" + phoneCode + "%")
      ));
    }
    if (sex != null) {
      predicates.add(builder.equal(root.get("sex"), sex));
    }
    if (function != null) {
      predicates.add(builder.or(
          builder.like(root.get("function"), "%" + function + "%"),
          builder.like(builder.lower(root.get("function")), "%" + function + "%")
      ));
    }
    if (hiredFrom != null && hiredTo != null) {
      predicates.add(builder.or(
          builder.between(root.get("hiringDate"), convertInstantToLocalDate(hiredFrom, zoneId), convertInstantToLocalDate(hiredTo, zoneId))
      ));
    }
    if (wentFrom != null && wentTo != null) {
      predicates.add(builder.or(
          builder.between(root.get("departureDate"), convertInstantToLocalDate(wentFrom, zoneId), convertInstantToLocalDate(wentTo, zoneId))
      ));
    }
    return new Predicate[predicates.size()];
  }

  private static LocalDate convertInstantToLocalDate(Instant instant, ZoneId zoneId) {
    return instant.atZone(zoneId).toLocalDate();
  }
  private static List<Order> retrieveOrders(OrderEnum firstNameOrder, OrderEnum lastNameOrder,
                                            OrderEnum sexOrder, OrderEnum functionOrder) {
    List<Order> orders = new ArrayList<>();
    if (firstNameOrder != null) {
      orders.add(new Order(getOrderValue(firstNameOrder), "firstName"));
    }
    if (lastNameOrder != null) {
      orders.add(new Order(getOrderValue(lastNameOrder), "lastName"));
    }
    if (sexOrder != null) {
      orders.add(new Order(getOrderValue(sexOrder), "sex"));
    }
    if (functionOrder != null) {
      orders.add(new Order(getOrderValue(functionOrder), "function"));
    }
    return orders;
  }

  private static Direction getOrderValue(OrderEnum orderEnum) {
    if (orderEnum == OrderEnum.ASC) {
      return Direction.ASC;
    }
    return Direction.DESC;
  }

  public Employee getById(String id) {
    Optional<EmployeeEntity> actual = repository.findById(id);
    if (actual.isPresent()) {
      return mapper.toDomain(actual.get());
    }
    throw new NotFoundException("The employee (id=" + id + ") is not found");
  }

  public List<Employee> getAllEmployees(Integer page, Integer pageSize) {
    Pageable pageable;
    if (page == null || pageSize == null) {
      pageable = PageRequest.of(0, 10);
    } else {
      pageable = PageRequest.of(page, pageSize);
    }
    return repository.findAll(pageable).toList().stream()
        .map(mapper::toDomain)
        .toList();
  }

  public List<Employee> getEmployeeByCriteria(String lastName, String firstName, SexEnum sex,
                                              String function,
                                              String hiredFrom,
                                              String hiredTo,
                                              String wentFrom,
                                              String wentTo, OrderEnum lastNameOrder,
                                              OrderEnum firstNameOrder, OrderEnum sexOrder,
                                              OrderEnum functionOrder, Integer page,
                                              Integer pageSize, String phoneCode) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<EmployeeEntity> query = criteriaBuilder.createQuery(EmployeeEntity.class);
    List<Order> orders = retrieveOrders(lastNameOrder, firstNameOrder, sexOrder, functionOrder);
    Pageable pageable = PageRequest.of(page, pageSize, Sort.by(orders));
    Root<EmployeeEntity> root = query.from(EmployeeEntity.class);
    Join<EmployeeEntity, PhoneNumberEntity> join = root.join("phoneNumbers");
    List<Predicate> predicates = new ArrayList<>();
    Predicate[] predicatesArray = retrieveNotNullPredicates(lastName, firstName, sex, function,
        fromStringToInstant(hiredFrom), fromStringToInstant(hiredTo),
        fromStringToInstant(wentFrom), fromStringToInstant(wentTo),
        criteriaBuilder, root, predicates, phoneCode, join);
    query
        .where(criteriaBuilder.and(predicates.toArray(predicatesArray)))
        .orderBy(QueryUtils.toOrders(pageable.getSort(), root, criteriaBuilder));

    return entityManager.createQuery(query)
        .setFirstResult((pageable.getPageNumber()) * pageable.getPageSize())
        .setMaxResults(pageable.getPageSize())
        .getResultList()
        .stream()
        .map(mapper::toDomain)
        .toList();
  }

  public Employee crupdateEmployee(Employee employee) {
    return mapper.toDomain(repository.save(mapper.toEntity(employee)));
  }

  public void exportDatasToCsv(List<EmployeeDatas> employees) {
    try (CSVWriter writer = new CSVWriter(new FileWriter("./files/employees.csv"))) {
      String[] header =
          {"Id", "First Name", "Last Name", "Birth Date", "Personal Number", "Sex", "Address",
              "Personal Email", "Professional Email", "Id Card Number", "Id Card Delivered Date",
              "Id Card Delivered Place", "Function", "Children In Charge", "Hiring Date",
              "Departure Date", "CNAPS Number", "Phone Numbers", "SPC"};
      writer.writeNext(header);
      for (EmployeeDatas employee : employees) {
        String[] rowData = {employee.getId(), employee.getFirstName(), employee.getLastName(),
            employee.getBirthDate(), employee.getPersonalNumber(),
            String.valueOf(employee.getSex()), employee.getAddress(), employee.getPersonalEmail()
            , employee.getProfessionalEmail(), employee.getIdCardNumber(),
            employee.getIdCardDeliveredDate(),
            employee.getIdCardDeliveredPlace(), employee.getFunction(),
            String.valueOf(employee.getChildrenInCharge()),
            employee.getHiringDate(),
            employee.getDepartureDate(), employee.getCnapsNumber(),
            employee.getPhoneNumbers().toString(), employee.getSpc().toString()};
        writer.writeNext(rowData);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
