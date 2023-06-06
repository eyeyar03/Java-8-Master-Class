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

    Scanner scanner = new Scanner(System.in);

    Optional<Integer> optionalEmployeeNumber;
    do {
      optionalEmployeeNumber = getEmployeeNumber();
    } while (!optionalEmployeeNumber.isPresent());

    int employeeNumber = optionalEmployeeNumber.get();

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
    userSelectionState.setAddedEmployeeNumber(addedEmployeeNumber);

    displaySuccessMessage(addedEmployeeNumber);

    CommandAction mainMenu = new MainMenuAction(userSelectionState);
    mainMenu.doAction();
  }

  private Optional<Integer> getEmployeeNumber() {
    Scanner scanner = new Scanner(System.in);

    Optional<Integer> optionalEmployeeNumber = Optional.empty();

    try {
      System.out.println("Employee Number: ");
      int employeeNumber = scanner.nextInt();

      optionalEmployeeNumber = Optional.of(employeeNumber);

    } catch (Exception e) {
      System.out.println("Invalid entry. Try again.");
    }

    return optionalEmployeeNumber;
  }

  private void displaySuccessMessage(int addedEmployeeNumber) {
    Optional<Employee> optionalEmployee =
        employeeService.getEmployeeByEmployeeNumber(addedEmployeeNumber);

    if (optionalEmployee.isPresent()) {
      System.out.println("Employee Record Added Successfully: " + LocalDateTime.now());
      System.out.println("Number: " + optionalEmployee.get().getEmployeeNumber());
      System.out.println(
          "Name: "
              + optionalEmployee.get().getFirstName()
              + " "
              + optionalEmployee.get().getMiddleName()
              + " "
              + optionalEmployee.get().getLastName());
      System.out.println("Date Hired: " + optionalEmployee.get().getHiringDate() + "\n");
    }
  }
}
