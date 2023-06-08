package com.masterclass.employee.directory.display;

import com.masterclass.employee.directory.model.Employee;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

import static com.masterclass.employee.directory.util.Constants.MESSAGE_EMPLOYEE_SUCCESSFULLY_ADDED;

public class DefaultDisplayForNewlyAddedEmployee implements Consumer<List<Employee>> {

  @Override
  public void accept(List<Employee> employees) {
    Employee newlyAddedEmployee = employees.get(0);

    System.out.println(MESSAGE_EMPLOYEE_SUCCESSFULLY_ADDED + LocalDateTime.now());
    System.out.println("Number: " + newlyAddedEmployee.getEmployeeNumber());
    System.out.println(
        "Name: "
            + newlyAddedEmployee.getFirstName()
            + " "
            + newlyAddedEmployee.getMiddleName()
            + " "
            + newlyAddedEmployee.getLastName());
    System.out.println("Date Hired: " + newlyAddedEmployee.getHiringDate() + "\n");
  }
}
