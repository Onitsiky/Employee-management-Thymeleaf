package app.employee.management.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CreateCompanyConfiguration {
  private String companyName;
  private String description;
  private String slogan;
  private String exactAddress;
  private String contactEmail;
  private String nif;
  private String stat;
  private String rcs;
  private MultipartFile logo;
  private List<String> phoneNumbers;
}
