package app.employee.management.repository.jpa;

import app.employee.management.model.Employee;
import app.employee.management.repository.entity.EmployeeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, String> {
}
