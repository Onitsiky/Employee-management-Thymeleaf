package app.employee.management.repository.jpa;

import app.employee.management.repository.entity.SPCEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SPCJpaRepository extends JpaRepository<SPCEntity, String> {
}
