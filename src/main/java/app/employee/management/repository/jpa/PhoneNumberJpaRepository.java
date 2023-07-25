package app.employee.management.repository.jpa;

import app.employee.management.repository.entity.PhoneNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberJpaRepository extends JpaRepository<PhoneNumberEntity, String> {
}
