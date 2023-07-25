package app.employee.management.repository.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Builder(toBuilder = true)
@Entity(name = "company_configuration")
@ToString
public class CompanyConfigurationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String companyName;
  private String description;
  private String slogan;
  private String exactAddress;
  private String contactEmail;
  private String nif;
  private String stat;
  private String rcs;
  private byte[] logo;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "company_configuration_id")
  private List<PhoneNumberEntity> phoneNumbers;
}
