package app.employee.management.controller;

import app.employee.management.service.EmployeeService;
import app.employee.management.view.mapper.EmployeeViewMapper;
import app.employee.management.view.model.CreateEmployee;
import app.employee.management.view.model.Employee;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class EmployeeController {
  private final EmployeeService service;
  private final EmployeeViewMapper mapper;
  private final CreateEmployeeValidator validator;

  @GetMapping("/employee")
  public String employeeList(@RequestParam(required = false) Integer page,
                             @RequestParam(required = false) Integer pageSize, Model model) {
    List<Employee> employees = service.getAllEmployees(page, pageSize).stream()
        .map(mapper::toViewModel)
        .toList();
    model.addAttribute("employees", employees);
    return "employee-list";
  }

  @GetMapping("/add-employee")
  public String employeeForm(Model model) {
    model.addAttribute("employee", new CreateEmployee());
    return "employee-form";
  }

  @PostMapping("/save-employee")
  public String saveEmployee(@ModelAttribute CreateEmployee employee) {
    validator.accept(employee);
    service.crupdateEmployee(mapper.toDomain(employee));
    return "redirect:/employee";
  }
}
