package app.employee.management.repository.mapper;

import app.employee.management.model.SPC;
import app.employee.management.repository.entity.SPCEntity;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SPCMapper {
  public SPCEntity toEntity(SPC domain) {
    return SPCEntity.builder()
        .id(domain.getId())
        .categoryName(domain.getCategoryName())
        .build();
  }

  public SPC toDomain(SPCEntity entity) {
    return SPC.builder()
        .id(entity.getId())
        .categoryName(entity.getCategoryName())
        .build();
  }

  public List<SPCEntity> toEntities(List<SPC> domains) {
    return domains.stream()
        .map(this::toEntity)
        .toList();
  }

  public List<SPC> toDomains(List<SPCEntity> entities) {
    return entities.stream()
        .map(this::toDomain)
        .toList();
  }
}
