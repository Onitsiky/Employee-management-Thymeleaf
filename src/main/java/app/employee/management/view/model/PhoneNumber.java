package app.employee.management.view.model;

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
@ToString
@Builder(toBuilder = true)
public class PhoneNumber {
  private String id;
  private String number;
  private String code;
}
