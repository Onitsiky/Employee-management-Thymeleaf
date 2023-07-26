package app.employee.management.controller;

import app.employee.management.model.CreateCompanyConfiguration;
import app.employee.management.service.CompanyConfigurationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class CompanyConfigurationController {
  private final CompanyConfigurationService service;

  @GetMapping("/company/configuration")
  public String companyConfigurationForm(Model model) {
    model.addAttribute("companyConfiguration", new CreateCompanyConfiguration());
    return "company-configuration";
  }

  @PostMapping("/company/configuration")
  public String saveCompanyconfiguration(@ModelAttribute CreateCompanyConfiguration companyConfiguration) {
    service.save(companyConfiguration);
    return "redirect:/employee";
  }
}
