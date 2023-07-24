package app.employee.management.model;

import app.employee.management.repository.enums.SexEnum;
import java.io.Serializable;
import java.time.Instant;
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
public class Employee implements Serializable {
  private String id;
  private String firstname;
  private String lastname;
  private Instant birthDate;
  private byte[] image;
  private String personalNumber;
  private SexEnum sex;
  private String address;
  private String personalEmail;
  private String professionalEmail;
  private String idCardNumber;
  private Instant idCardDeliveredDate;
  private String idCardDeliveredPlace;
  private String function;
  private Integer childrenInCharge;
  private Instant hiringDate;
  private Instant departureDate;
  private String cnapsNumber;
  private List<PhoneNumber> phoneNumbers;
  private List<SocioProfessionalCategory> scp;
}
