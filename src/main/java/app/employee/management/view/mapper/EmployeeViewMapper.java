package app.employee.management.view.mapper;


import app.employee.management.view.model.CreateEmployee;
import app.employee.management.view.model.Employee;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class EmployeeViewMapper {
  public static Instant fromStringToInstant(String str) {
    LocalDate localDate = LocalDate.parse(str);
    return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
  }

  public static String fromInstantToString (Instant instant) {
    LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return dateTime.format(formatter);
  }

  public static byte[] fromMultipartFileToByte(MultipartFile file) {
    if (file != null) {
      try {
        return file.getBytes();
      } catch (IOException e) {
        throw new RuntimeException("Error on mapping image");
      }
    }
    return null;
  }

  public static String fromByteToBase64(byte[] file) {
    if (file != null) {
      return Base64.getEncoder().encodeToString(file);
    }
    return null;
  }

  public static byte[] fromBase64ToByte(String base64) {
    if (base64 != null) {
      return Base64.getDecoder().decode(base64);
    }
    return null;
  }

  public Employee toViewModel(app.employee.management.model.Employee domain) {
    return Employee.builder()
        .id(domain.getId())
        .firstName(domain.getFirstname())
        .lastName(domain.getLastname())
        .birthDate(fromInstantToString(domain.getBirthDate()))
        .build();
  }

  public app.employee.management.model.Employee toDomain(CreateEmployee view) {
    return app.employee.management.model.Employee.builder()
        .firstname(view.getFirstName())
        .lastname(view.getLastName())
        .birthDate(fromStringToInstant(view.getBirthDate()))
        .build();
  }
}
