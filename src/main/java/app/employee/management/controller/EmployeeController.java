package app.employee.management.controller;

import app.employee.management.controller.validator.CreateEmployeeValidator;
import app.employee.management.repository.enums.SexEnum;
import app.employee.management.service.EmployeeService;
import app.employee.management.service.SPCService;
import app.employee.management.view.mapper.EmployeeViewMapper;
import app.employee.management.view.mapper.SPCViewMapper;
import app.employee.management.view.model.CreateEmployee;
import app.employee.management.view.model.Employee;
import app.employee.management.view.model.SPC;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@Slf4j
public class EmployeeController {
  private final EmployeeService service;
  private final EmployeeViewMapper mapper;
  private final CreateEmployeeValidator validator;
  private final SPCService spcService;
  private final SPCViewMapper spcViewMapper;
  @GetMapping("/employee")
  public String employeeList(@RequestParam(required = false) Integer page,
                             @RequestParam(required = false) Integer pageSize, Model model) {
    List<Employee> employees = service.getAllEmployees(page, pageSize).stream()
        .map(mapper::toViewModel)
        .toList();
    model.addAttribute("employees", employees);
    return "employee-list";
  }

  @GetMapping("/employee-details")
  public String EmployeeDetails(Model model, @RequestParam("id") String id) {
    Employee subject = mapper.toViewModel(service.getById(id));
    model.addAttribute("employee", subject);
    model.addAttribute("sexEnumM", SexEnum.M);
    model.addAttribute("sexEnumF", SexEnum.F);
    return "employee-details";
  }

  @GetMapping("/add-employee")
  public String employeeForm(Model model) {
    List<SPC> spcs = spcViewMapper.toViewModels(spcService.getAll());
    model.addAttribute("employee", new CreateEmployee());
    model.addAttribute("sexEnumM", SexEnum.M);
    model.addAttribute("sexEnumF", SexEnum.F);
    model.addAttribute("socioProCategories", spcs);
    return "employee-form";
  }

  @PostMapping("/save-employee")
  public String saveEmployee(@ModelAttribute CreateEmployee employee) {
    validator.accept(employee);
    service.crupdateEmployee(mapper.toDomain(employee));
    return "redirect:/employee";
  }

  @PostMapping("/update-employee")
  public String updateEmployee(@ModelAttribute Employee employee) {
    service.crupdateEmployee(mapper.toDomain(employee));
    return "redirect:/employee";
  }
}
