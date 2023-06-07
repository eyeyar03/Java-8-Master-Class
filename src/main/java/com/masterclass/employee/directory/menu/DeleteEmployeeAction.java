package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import com.masterclass.employee.directory.util.InputHelper;
import java.util.Optional;

public class DeleteEmployeeAction implements CommandAction {

  private EmployeeService employeeService = new EmployeeServiceImpl();
  private final UserSelectionState userSelectionState;

  public DeleteEmployeeAction(UserSelectionState userSelectionState) {
    this.userSelectionState = userSelectionState;
  }

  @Override
  public void doAction() {
    int employeeNumber = InputHelper.askUserToProvideEmployeeNumber();

    Optional<Employee> optionalEmployee =
        employeeService.getEmployeeByEmployeeNumber(employeeNumber);

    if (optionalEmployee.isPresent()) {
      employeeService.deleteEmployeeByEmployeeNumber(employeeNumber);

      Employee deletedEmployee = optionalEmployee.get();
      System.out.println(
          String.format(
              "Deleted [%d] %s %s %s (%s).",
              deletedEmployee.getEmployeeNumber(),
              deletedEmployee.getFirstName(),
              deletedEmployee.getMiddleName(),
              deletedEmployee.getLastName(),
              deletedEmployee.getHiringDate()));

    } else {
      System.out.println(
          String.format("Employee with employee number %d cannot be found!\n", employeeNumber));
    }

    userSelectionState.getPreviousCommandActions().pop().doAction();
  }
}
