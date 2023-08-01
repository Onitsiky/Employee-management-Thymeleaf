package app.employee.management.view.model;

import app.employee.management.repository.enums.SexEnum;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@EqualsAndHashCode
public class CreateEmployee {
  private String firstName;
  private String lastName;
  private String birthDate;
  private MultipartFile image;
  private SexEnum sex;
  private String address;
  private String personalEmail;
  private String professionalEmail;
  private String idCardNumber;
  private String idCardDeliveredDate;
  private String idCardDeliveredPlace;
  private String function;
  private Integer childrenInCharge;
  private String hiringDate;
  private String departureDate;
  private String cnapsNumber;
  private List<String> socioProfessionalCategories;
  private List<String> phoneNumbers;
  private List<String> phoneCode;
}
