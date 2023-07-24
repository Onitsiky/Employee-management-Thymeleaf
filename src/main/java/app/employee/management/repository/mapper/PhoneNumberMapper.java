package app.employee.management.repository.mapper;

import app.employee.management.model.PhoneNumber;
import app.employee.management.repository.entity.PhoneNumberEntity;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberMapper {
  public PhoneNumberEntity toEntity(PhoneNumber domain) {
    return PhoneNumberEntity.builder()
        .id(domain.getId())
        .number(domain.getNumber())
        .build();
  }

  public PhoneNumber toDomain(PhoneNumberEntity entity) {
    return PhoneNumber.builder()
        .id(entity.getId())
        .number(entity.getNumber())
        .build();
  }

  public List<PhoneNumberEntity> toEntities(List<PhoneNumber> domains) {
    return domains.stream()
        .map(this::toEntity)
        .toList();
  }

  public List<PhoneNumber> phoneNumbers(List<PhoneNumberEntity> entities) {
    return entities.stream()
        .map(this::toDomain)
        .toList();
  }
}
