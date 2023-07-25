package app.employee.management.repository.jpa;

import app.employee.management.repository.entity.CompanyConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyConfigurationJpaRepository extends JpaRepository<CompanyConfigurationEntity, String> {
  CompanyConfigurationEntity getByCompanyName(String name);
}
