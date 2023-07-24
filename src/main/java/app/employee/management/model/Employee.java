package app.employee.management.model;

import java.io.Serializable;
import java.time.Instant;
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
}
