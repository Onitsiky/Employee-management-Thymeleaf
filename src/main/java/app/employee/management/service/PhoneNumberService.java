package app.employee.management.service;

import app.employee.management.model.PhoneNumber;
import app.employee.management.repository.jpa.PhoneNumberJpaRepository;
import app.employee.management.repository.mapper.PhoneNumberMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PhoneNumberService {
  private final PhoneNumberJpaRepository repository;
  private final PhoneNumberMapper mapper;

  public List<PhoneNumber> saveAll(List<PhoneNumber> toSave) {
    return repository.saveAll(mapper.toEntities(toSave)).stream()
        .map(mapper::toDomain)
        .toList();
  }

  public boolean checkUniquePhoneNumber(String code, String number) {
    List<PhoneNumber> actuals =
        repository.getByCodeAndAndNumber(code, number).stream()
            .map(mapper::toDomain)
            .toList();
    if(actuals.size() > 0) {
      return false;
    }
    return true;
  }
}
