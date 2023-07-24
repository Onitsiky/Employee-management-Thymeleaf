package app.employee.management.service;

import app.employee.management.model.Employee;
import app.employee.management.repository.jpa.EmployeeJpaRepository;
import app.employee.management.repository.mapper.EmployeeMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {
  private final EmployeeJpaRepository repository;
  private final EmployeeMapper mapper;

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
}
