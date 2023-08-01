package app.employee.management.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
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
@Entity(name = "session")
@ToString
public class SessionEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String sessionId;
  private Instant timeout;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;
}