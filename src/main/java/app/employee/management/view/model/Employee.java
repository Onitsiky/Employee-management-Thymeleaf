package app.employee.management.view.model;

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
public class Employee {
  private String id;
  private String firstName;
  private String lastName;
  private String birthDate;
  private String image;
  private String personalNumber;
}
