package app.employee.management.model;

import java.time.Instant;
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
@ToString
public class Session {
  private String id;
  private String sessionId;
  private Instant timeout;
  private User user;
}
