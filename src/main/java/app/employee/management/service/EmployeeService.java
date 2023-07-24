package app.employee.management.service;

import app.employee.management.model.Employee;
import app.employee.management.repository.entity.EmployeeEntity;
import app.employee.management.repository.jpa.EmployeeJpaRepository;
import app.employee.management.repository.mapper.EmployeeMapper;
import app.employee.management.utils.exception.NotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {
  private final EmployeeJpaRepository repository;
  private final EmployeeMapper mapper;

  public Employee getById(String id) {
    Optional<EmployeeEntity> actual = repository.findById(id);
    if(actual.isPresent()) {
      log.info("Employee: {}", actual.get());
      return mapper.toDomain(actual.get());
    }
    throw new NotFoundException("The employee (id=" + id + ") is not found");
  }

  public List<Employee> getAllEmployees(Integer page, Integer pageSize) {
    Pageable pageable;
    if(page == null || pageSize == null) {
      pageable = PageRequest.of(0,10);
    } else {
       pageable = PageRequest.of(page, pageSize);
    }
    return repository.findAll(pageable).toList().stream()
        .map(mapper::toDomain)
        .toList();
  }

  public Employee crupdateEmployee(Employee employee) {
    return mapper.toDomain(repository.save(mapper.toEntity(employee)));
  }
}
