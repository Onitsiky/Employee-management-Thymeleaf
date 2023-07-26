package app.employee.management.view.model;

import app.employee.management.repository.enums.SexEnum;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Component
public class UpdateEmployee {
  private String id;
  private String firstName;
  private String lastName;
  private String birthDate;
  private String image;
  private String personalNumber;
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
  private List<String> phoneNumbersId;
  private List<String> phoneNumbers;
  private List<String> spcIds;
  private List<String> spcs;
}
