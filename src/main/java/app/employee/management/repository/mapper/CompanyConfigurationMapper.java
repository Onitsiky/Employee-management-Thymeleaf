package app.employee.management.repository.mapper;

import app.employee.management.model.CompanyConfiguration;
import app.employee.management.repository.entity.CompanyConfigurationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CompanyConfigurationMapper {
  private final PhoneNumberMapper phoneNumberMapper;

  public CompanyConfiguration toDomain(CompanyConfigurationEntity entity) {
    return CompanyConfiguration.builder()
        .id(entity.getId())
        .companyName(entity.getCompanyName())
        .description(entity.getDescription())
        .contactEmail(entity.getContactEmail())
        .exactAddress(entity.getExactAddress())
        .slogan(entity.getSlogan())
        .nif(entity.getNif())
        .stat(entity.getStat())
        .rcs(entity.getRcs())
        .logo(entity.getLogo())
        .phoneNumbers(entity.getPhoneNumbers().stream()
            .map(phoneNumberMapper::toDomain)
            .toList())
        .build();
  }
}
