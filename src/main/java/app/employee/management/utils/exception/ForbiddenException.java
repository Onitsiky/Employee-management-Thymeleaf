package app.employee.management.utils.exception;

import org.springframework.stereotype.Component;

@Component
public class ForbiddenException extends RuntimeException{
  public ForbiddenException() {
    super();
  }

  public ForbiddenException(String message) {
    super(message);
  }

  public ForbiddenException(String message, Throwable cause) {
    super(message, cause);
  }

  public ForbiddenException(Throwable cause) {
    super(cause);
  }
}