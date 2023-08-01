package app.employee.management.controller;

import app.employee.management.service.SessionService;
import app.employee.management.utils.exception.ForbiddenException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@AllArgsConstructor
@ControllerAdvice
public class GlobalControllerAdvice {
  private SessionService service;

  @ModelAttribute
  public void authentication(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) throws
      IOException {
    if (!request.getRequestURI().contains("log")) {
      try {
        service.checkConnection(httpSession.getId());
      } catch (ForbiddenException e) {
        response.sendRedirect("/login");
      }
    }
  }
}
