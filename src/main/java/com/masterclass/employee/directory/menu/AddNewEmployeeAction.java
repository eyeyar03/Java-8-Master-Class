package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.display.DisplaySupplier;
import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import com.masterclass.employee.directory.util.InputHelper;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class AddNewEmployeeAction implements CommandAction {

  private EmployeeService employeeService = new EmployeeServiceImpl();

  private final UserSelectionState userSelectionState;

  public AddNewEmployeeAction(UserSelectionState userSelectionState) {
    this.userSelectionState = userSelectionState;
  }

  @Override
  public void doAction() {
    int employeeNumber = InputHelper.askUserToProvideEmployeeNumber();

    Scanner scanner = new Scanner(System.in);
    System.out.println("First name: ");
    String firstName = scanner.nextLine();

    System.out.println("Last name: ");
    String lastName = scanner.nextLine();

    System.out.println("Middle Name: ");
    String middleName = scanner.nextLine();

    System.out.println("Hiring Date: ");
    String hiringDate = scanner.nextLine();

    Employee employee =
        Employee.builder()
            .employeeNumber(employeeNumber)
            .firstName(firstName)
            .lastName(lastName)
            .middleName(middleName)
            .hiringDate(hiringDate)
            .build();

    int addedEmployeeNumber = employeeService.addEmployee(employee);

    displaySuccessMessage(addedEmployeeNumber);

    userSelectionState.getPreviousCommandActions().pop().doAction();
  }

  private void displaySuccessMessage(int addedEmployeeNumber) {
    Optional<Employee> optionalEmployee =
        employeeService.getEmployeeByEmployeeNumber(addedEmployeeNumber);

    if (optionalEmployee.isPresent()) {
      DisplaySupplier.getDefaultDisplayForNewlyAddedEmployee()
          .accept(Arrays.asList(optionalEmployee.get()));
    }
  }
}
