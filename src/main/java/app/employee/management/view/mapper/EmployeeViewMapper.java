package app.employee.management.view.mapper;


import app.employee.management.service.PhoneNumberService;
import app.employee.management.service.SPCService;
import app.employee.management.view.model.CreateEmployee;
import app.employee.management.view.model.Employee;
import app.employee.management.view.model.PhoneNumber;
import app.employee.management.view.model.SPC;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@AllArgsConstructor
@Slf4j
public class EmployeeViewMapper {
  private final PhoneNumberViewMapper phoneNumberViewMapper;
  private final SPCViewMapper spcViewMapper;
  private final SPCService spcService;

  public static Instant fromStringToInstant(String str) {
    if(!str.isEmpty() && !str.isBlank()){
      LocalDate localDate = LocalDate.parse(str);
      return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }
      return null;
  }

  public static String fromInstantToString(Instant instant) {
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
        .image(fromByteToBase64(domain.getImage()))
        .personalNumber(domain.getPersonalNumber())
        .sex(domain.getSex())
        .address(domain.getAddress())
        .personalEmail(domain.getPersonalEmail())
        .professionalEmail(domain.getProfessionalEmail())
        .idCardNumber(domain.getIdCardNumber())
        .idCardDeliveredDate(fromInstantToString(domain.getIdCardDeliveredDate()))
        .idCardDeliveredPlace(domain.getIdCardDeliveredPlace())
        .function(domain.getFunction())
        .childrenInCharge(domain.getChildrenInCharge())
        .hiringDate(fromInstantToString(domain.getHiringDate()))
        .departureDate(fromInstantToString(domain.getDepartureDate()))
        .cnapsNumber(domain.getCnapsNumber())
        .phoneNumbers(phoneNumberViewMapper.toViewModels(domain.getPhoneNumbers()))
        .spc(spcViewMapper.toViewModels(domain.getSpc()))
        .build();
  }

  public app.employee.management.model.Employee toDomain(CreateEmployee view) {
    List<PhoneNumber> phoneNumbers = view.getPhoneNumbers().stream()
        .map(number -> PhoneNumber.builder()
            .number(number)
            .build())
        .toList();
    List<SPC> spcs = view.getSocioProfessionalCategories().stream()
        .map(id -> spcViewMapper.toViewModel(spcService.getById(id)))
        .toList();
    return app.employee.management.model.Employee.builder()
        .firstname(view.getFirstName())
        .lastname(view.getLastName())
        .birthDate(fromStringToInstant(view.getBirthDate()))
        .image(fromMultipartFileToByte(view.getImage()))
        .sex(view.getSex())
        .address(view.getAddress())
        .personalEmail(view.getPersonalEmail())
        .professionalEmail(view.getProfessionalEmail())
        .idCardNumber(view.getIdCardNumber())
        .idCardDeliveredDate(fromStringToInstant(view.getIdCardDeliveredDate()))
        .idCardDeliveredPlace(view.getIdCardDeliveredPlace())
        .function(view.getFunction())
        .childrenInCharge(view.getChildrenInCharge())
        .hiringDate(fromStringToInstant(view.getHiringDate()))
        .departureDate(fromStringToInstant(view.getDepartureDate()))
        .cnapsNumber(view.getCnapsNumber())
        .phoneNumbers(phoneNumberViewMapper.toDomains(phoneNumbers))
        .spc(spcViewMapper.toDomains(spcs))
        .build();
  }

  public app.employee.management.model.Employee toDomain(Employee view) {
    return app.employee.management.model.Employee.builder()
        .id(view.getId())
        .firstname(view.getFirstName())
        .lastname(view.getLastName())
        .birthDate(fromStringToInstant(view.getBirthDate()))
        .image(fromBase64ToByte(view.getImage()))
        .personalNumber(view.getPersonalNumber())
        .sex(view.getSex())
        .address(view.getAddress())
        .personalEmail(view.getPersonalEmail())
        .professionalEmail(view.getProfessionalEmail())
        .idCardNumber(view.getIdCardNumber())
        .idCardDeliveredDate(fromStringToInstant(view.getIdCardDeliveredDate()))
        .idCardDeliveredPlace(view.getIdCardDeliveredPlace())
        .function(view.getFunction())
        .childrenInCharge(view.getChildrenInCharge())
        .hiringDate(fromStringToInstant(view.getHiringDate()))
        .departureDate(fromStringToInstant(view.getDepartureDate()))
        .cnapsNumber(view.getCnapsNumber())
        .phoneNumbers(phoneNumberViewMapper.toDomains(view.getPhoneNumbers()))
        .spc(spcViewMapper.toDomains(view.getSpc()))
        .build();
  }
}
