package app.employee.management.repository.jpa;

import app.employee.management.repository.entity.PhoneNumberEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberJpaRepository extends JpaRepository<PhoneNumberEntity, String> {
  List<PhoneNumberEntity> getByCodeAndAndNumber(String code, String number);
}
