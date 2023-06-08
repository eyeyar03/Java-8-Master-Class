package com.masterclass.employee.directory.menu.display;

import static com.masterclass.employee.directory.util.Constants.MESSAGE_EMPLOYEE_SUCCESSFULLY_ADDED;

import com.masterclass.employee.directory.menu.CommandAction;
import com.masterclass.employee.directory.model.Employee;
import java.time.LocalDateTime;

public class DisplayNewlyAddedEmployeeAction implements CommandAction {

  private final Employee employee;

  public DisplayNewlyAddedEmployeeAction(Employee employee) {
    this.employee = employee;
  }

  @Override
  public void doAction() {
    System.out.println(MESSAGE_EMPLOYEE_SUCCESSFULLY_ADDED + LocalDateTime.now());
    System.out.println("Number: " + employee.getEmployeeNumber());
    System.out.println(
        "Name: "
            + employee.getFirstName()
            + " "
            + employee.getMiddleName()
            + " "
            + employee.getLastName());
    System.out.println("Date Hired: " + employee.getHiringDate() + "\n");
  }
}
