package app.employee.management.controller;

import app.employee.management.model.User;
import app.employee.management.service.SessionService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AuthController {
  private final SessionService service;

  @GetMapping("/login")
  public String loginPage(Model model) {
    model.addAttribute("user", new User());
    return "login-form";
  }

  @PostMapping("/log")
  public String logUser(@ModelAttribute User user, HttpSession session) {
    service.authenticateUser(user, session.getId());
    return "redirect:/employee";
  }

  @GetMapping("/logout")
  public String logoutUser(HttpSession session) {
    service.endSession(session.getId());
    return "redirect:/employee";
  }
}
