package app.employee.management.repository.mapper;

import app.employee.management.model.Employee;
import app.employee.management.repository.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
  public EmployeeEntity toEntity(Employee domain) {
    return EmployeeEntity.builder()
        .id(domain.getId())
        .firstName(domain.getFirstname())
        .lastName(domain.getLastname())
        .birthDate(domain.getBirthDate())
        .image(domain.getImage())
        .personalNumber(domain.getPersonalNumber())
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
        .build();
  }
}
