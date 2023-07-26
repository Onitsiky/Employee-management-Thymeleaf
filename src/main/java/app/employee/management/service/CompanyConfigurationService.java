package app.employee.management.service;

import app.employee.management.model.CompanyConfiguration;
import app.employee.management.model.CreateCompanyConfiguration;
import app.employee.management.repository.jpa.CompanyConfigurationJpaRepository;
import app.employee.management.repository.mapper.CompanyConfigurationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyConfigurationService {
  private final CompanyConfigurationJpaRepository repository;
  private final CompanyConfigurationMapper mapper;

  public CompanyConfiguration getCompanyByName(String name) {
    return mapper.toDomain(repository.getByCompanyName(name));
  }

  public CompanyConfiguration save(CreateCompanyConfiguration companyConfiguration) {
    return mapper.toDomain(repository.save(mapper.toEntity(companyConfiguration)));
  }
}
