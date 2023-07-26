package app.employee.management.repository.mapper;

import app.employee.management.model.CompanyConfiguration;
import app.employee.management.model.CreateCompanyConfiguration;
import app.employee.management.model.PhoneNumber;
import app.employee.management.repository.entity.CompanyConfigurationEntity;
import app.employee.management.repository.entity.PhoneNumberEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static app.employee.management.view.mapper.EmployeeViewMapper.fromByteToBase64;
import static app.employee.management.view.mapper.EmployeeViewMapper.fromMultipartFileToByte;

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
        .logo(fromByteToBase64(entity.getLogo()))
        .phoneNumbers(entity.getPhoneNumbers().stream()
            .map(phoneNumberMapper::toDomain)
            .toList())
        .build();
  }
  public CompanyConfigurationEntity toEntity(CreateCompanyConfiguration domain) {
    List<PhoneNumberEntity> phoneNumbers = domain.getPhoneNumbers().stream()
        .map(phoneNumber -> PhoneNumberEntity.builder()
            .number(phoneNumber)
            .build())
        .toList();
    return CompanyConfigurationEntity.builder()
        .companyName(domain.getCompanyName())
        .description(domain.getDescription())
        .contactEmail(domain.getContactEmail())
        .exactAddress(domain.getExactAddress())
        .slogan(domain.getSlogan())
        .nif(domain.getNif())
        .stat(domain.getStat())
        .rcs(domain.getRcs())
        .logo(fromMultipartFileToByte(domain.getLogo()))
        .phoneNumbers(phoneNumbers)
        .build();
  }
}
