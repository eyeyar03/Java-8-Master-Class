package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import java.time.LocalDateTime;
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
    System.out.println("You selected Add New Employee Record");

    Scanner scan = new Scanner(System.in);
    System.out.println("Employee Number: ");

    int employeeNumber = scan.nextInt();

    scan.nextLine();
    System.out.println("First name: ");
    String firstName = scan.nextLine();

    System.out.println("Last name: ");
    String lastName = scan.nextLine();

    System.out.println("Middle Name: ");
    String middleName = scan.nextLine();

    System.out.println("Hiring Date: ");
    String hiringDate = scan.nextLine();

    Employee employee =
        Employee.builder()
            .employeeNumber(employeeNumber)
            .firstName(firstName)
            .lastName(lastName)
            .middleName(middleName)
            .hiringDate(hiringDate)
            .build();

    int addedEmployeeNumber = employeeService.addEmployee(employee);
    userSelectionState.setAddedEmployeeNumber(addedEmployeeNumber);

    displaySuccessMessage(addedEmployeeNumber);

    CommandAction mainMenu = new MainMenuAction(userSelectionState);
    mainMenu.doAction();
  }

  private void displaySuccessMessage(int addedEmployeeNumber) {
    Optional<Employee> optionalEmployee =
        employeeService.getEmployeeByEmployeeNumber(addedEmployeeNumber);

    if (optionalEmployee.isPresent()) {
      System.out.println("Employee Record Added Successfully: " + LocalDateTime.now());
      System.out.println("Number: " + optionalEmployee.get().getEmployeeNumber());
      System.out.println("Name: "
              + optionalEmployee.get().getFirstName() + " "
              + optionalEmployee.get().getMiddleName() + " "
              + optionalEmployee.get().getLastName());
      System.out.println("Date Hired: " + optionalEmployee.get().getHiringDate() + "\n");
    }
  }
}
