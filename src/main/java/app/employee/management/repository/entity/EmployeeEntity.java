package app.employee.management.repository.entity;

import app.employee.management.repository.enums.SexEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnTransformer;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Builder(toBuilder = true)
@Entity(name = "employee")
@ToString
public class EmployeeEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String firstName;
  private String lastName;
  private Instant birthDate;
  private byte[] image;
  private String personalNumber;
  @Enumerated(EnumType.STRING)
  @ColumnTransformer(read = "CAST (sex AS varchar)", write = "CAST (? AS gender)")
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
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "employee_id")
  private List<PhoneNumberEntity> phoneNumbers;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "employee_category", joinColumns = @JoinColumn(name = "employee_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  private List<SocioProfessionalCategoryEntity> scpCategories;
}
