package app.employee.management.repository.mapper;

import app.employee.management.model.Employee;
import app.employee.management.repository.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeMapper {
  private final PhoneNumberMapper phoneNumberMapper;
  private final SCPMapper scpMapper;

  public EmployeeEntity toEntity(Employee domain) {
    return EmployeeEntity.builder()
        .id(domain.getId())
        .firstName(domain.getFirstname())
        .lastName(domain.getLastname())
        .birthDate(domain.getBirthDate())
        .image(domain.getImage())
        .personalNumber(domain.getPersonalNumber())
        .sex(domain.getSex())
        .address(domain.getAddress())
        .personalEmail(domain.getPersonalEmail())
        .professionalEmail(domain.getProfessionalEmail())
        .idCardNumber(domain.getIdCardNumber())
        .idCardDeliveredDate(domain.getIdCardDeliveredDate())
        .idCardDeliveredPlace(domain.getIdCardDeliveredPlace())
        .function(domain.getFunction())
        .childrenInCharge(domain.getChildrenInCharge())
        .hiringDate(domain.getHiringDate())
        .departureDate(domain.getDepartureDate())
        .cnapsNumber(domain.getCnapsNumber())
        .phoneNumbers(phoneNumberMapper.toEntities(domain.getPhoneNumbers()))
        .scpCategories(scpMapper.toEntities(domain.getScp()))
        .build();
  }

  public Employee toDomain(EmployeeEntity entity) {
    return Employee.builder()
        .id(entity.getId())
        .firstname(entity.getFirstName())
        .lastname(entity.getLastName())
        .birthDate(entity.getBirthDate())
        .image(entity.getImage())
        .personalNumber(entity.getPersonalNumber())
        .sex(entity.getSex())
        .address(entity.getAddress())
        .personalEmail(entity.getPersonalEmail())
        .professionalEmail(entity.getProfessionalEmail())
        .idCardNumber(entity.getIdCardNumber())
        .idCardDeliveredDate(entity.getIdCardDeliveredDate())
        .idCardDeliveredPlace(entity.getIdCardDeliveredPlace())
        .function(entity.getFunction())
        .childrenInCharge(entity.getChildrenInCharge())
        .hiringDate(entity.getHiringDate())
        .departureDate(entity.getDepartureDate())
        .cnapsNumber(entity.getCnapsNumber())
        .scp(scpMapper.toDomains(entity.getScpCategories()))
        .phoneNumbers(phoneNumberMapper.phoneNumbers(entity.getPhoneNumbers()))
        .build();
  }
}
