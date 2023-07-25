package app.employee.management.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CompanyConfiguration {
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
  private List<PhoneNumber> phoneNumbers;
}
