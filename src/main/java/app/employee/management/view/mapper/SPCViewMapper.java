package app.employee.management.view.mapper;

import app.employee.management.view.model.SPC;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SPCViewMapper {
  public SPC toViewModel(app.employee.management.model.SPC domain) {
    return SPC.builder()
        .id(domain.getId())
        .categoryName(domain.getCategoryName())
        .build();
  }

  public app.employee.management.model.SPC toDomain(SPC viewModel) {
    return app.employee.management.model.SPC.builder()
        .id(viewModel.getId())
        .categoryName(viewModel.getCategoryName())
        .build();
  }

  public List<SPC> toViewModels(List<app.employee.management.model.SPC> domains) {
    return domains.stream()
        .map(this::toViewModel)
        .toList();
  }

  public List<app.employee.management.model.SPC> toDomains(List<SPC> viewModels) {
    return viewModels.stream()
        .map(this::toDomain)
        .toList();
  }
}
