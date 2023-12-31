package app.employee.management.controller.validator;

import app.employee.management.utils.exception.BadRequestException;
import app.employee.management.view.model.CreateEmployee;
import java.time.Instant;
import java.util.function.Consumer;
import org.springframework.stereotype.Component;

import static app.employee.management.view.mapper.EmployeeViewMapper.fromStringToInstant;

@Component
public class CreateEmployeeValidator implements Consumer<CreateEmployee> {
  @Override
  public void accept(CreateEmployee subject) {
    if(subject.getFirstName() == null ) {
      throw new BadRequestException("First name is mandatory.");
    }
    if(subject.getLastName() == null ) {
      throw new BadRequestException("Last name is mandatory.");
    }
    if(subject.getBirthDate() == null) {
      throw new BadRequestException("Birth date is mandatory.");
    } else if (fromStringToInstant(subject.getBirthDate()).isAfter(Instant.now())) {
      throw new BadRequestException("Birth date can't be future.");
    }
    if(subject.getChildrenInCharge() < 0) {
      throw new BadRequestException("The number of children in charge cannot be less than 0");
    }
    if(fromStringToInstant(subject.getHiringDate()).isAfter(Instant.now())) {
      throw new BadRequestException("Hiring date cannot be future");
    }
    if (subject.getHiringDate() == null) {
      throw new BadRequestException("Hiring date is mandatory.");
    }
    for (String phoneNumber : subject.getPhoneNumbers()) {
      if(phoneNumber.length() > 10) {
        throw new BadRequestException("[Phone number(" + phoneNumber + ") invalid] Entered phone " +
            "number is too" +
            " long." +
            " " +
            "Must not exceed 10.");
      }
      if(phoneNumber.length() < 10) {
        throw new BadRequestException("[Phone number (" + phoneNumber + ") invalid] Entered phone number is too short." +
            " " +
            "Must be 10.");
      }
    }
  }
}
