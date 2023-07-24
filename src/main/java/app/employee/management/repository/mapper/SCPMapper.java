package app.employee.management.repository.mapper;

import app.employee.management.model.SocioProfessionalCategory;
import app.employee.management.repository.entity.SocioProfessionalCategoryEntity;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SCPMapper {
  public SocioProfessionalCategoryEntity toEntity(SocioProfessionalCategory domain) {
    return SocioProfessionalCategoryEntity.builder()
        .id(domain.getId())
        .categoryName(domain.getCategoryName())
        .build();
  }

  public SocioProfessionalCategory toDomain(SocioProfessionalCategoryEntity entity) {
    return SocioProfessionalCategory.builder()
        .id(entity.getId())
        .categoryName(entity.getCategoryName())
        .build();
  }

  public List<SocioProfessionalCategoryEntity> toEntities(List<SocioProfessionalCategory> domains) {
    return domains.stream()
        .map(this::toEntity)
        .toList();
  }

  public List<SocioProfessionalCategory> toDomains(List<SocioProfessionalCategoryEntity> entities) {
    return entities.stream()
        .map(this::toDomain)
        .toList();
  }
}
