package app.employee.management.view.mapper;

import app.employee.management.view.model.PhoneNumber;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberViewMapper {
  public PhoneNumber toViewModel(app.employee.management.model.PhoneNumber domain) {
    return PhoneNumber.builder()
        .id(domain.getId())
        .number(domain.getNumber())
        .code(domain.getCode())
        .build();
  }

  public app.employee.management.model.PhoneNumber toDomain(PhoneNumber view) {
    return app.employee.management.model.PhoneNumber.builder()
        .id(view.getId())
        .number(view.getNumber())
        .code(view.getCode())
        .build();
  }

  public List<app.employee.management.model.PhoneNumber> toDomain(List<String> view) {
    return view.stream()
        .map(number -> app.employee.management.model.PhoneNumber.builder()
            .number(number)
            .build())
        .toList();
  }

  public List<PhoneNumber> toViewModels(List<app.employee.management.model.PhoneNumber> domains) {
    return domains.stream()
        .map(this::toViewModel)
        .toList();
  }

  public List<app.employee.management.model.PhoneNumber> toDomains(List<PhoneNumber> views) {
    return views.stream()
        .map(this::toDomain)
        .toList();
  }
}
