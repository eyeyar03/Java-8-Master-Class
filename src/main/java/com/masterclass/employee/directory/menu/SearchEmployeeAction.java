package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.display.DisplaySupplier;
import com.masterclass.employee.directory.menu.option.Option;
import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import com.masterclass.employee.directory.util.OptionsHelper;
import com.masterclass.employee.directory.util.SortEnum;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

@Option(label = "Search by Employee Number", key = 1)
@Option(label = "Search by First Name", key = 2)
@Option(label = "Search by Middle Name", key = 3)
@Option(label = "Search by Last Name", key = 4)
@Option(label = "Search by Hiring Date", key = 5)
@Option(label = "Back", key = -1)
public class SearchEmployeeAction implements CommandAction {

  private final Map<Integer, Runnable> searchesMap;

  {
    searchesMap = new HashMap<>();
    searchesMap.put(1, this::searchByEmployeeNumber);
    searchesMap.put(2, this::searchByFirstName);
    searchesMap.put(3, this::searchByMiddleName);
    searchesMap.put(4, this::searchByLastName);
    searchesMap.put(5, this::searchByHiringDate);
  }

  private EmployeeService employeeService = new EmployeeServiceImpl();
  private final UserSelectionState userSelectionState;

  public SearchEmployeeAction(UserSelectionState userSelectionState) {
    this.userSelectionState = userSelectionState;
  }

  @Override
  public void doAction() {
    OptionsHelper.printOptions(this.getClass(), "Choose an action", "Select action: ");

    Scanner scan = new Scanner(System.in);
    int choice = scan.nextInt();

    if (choice == -1) {
      userSelectionState.getPreviousCommandActions().pop().doAction();
      return;
    }

    Runnable runnable = searchesMap.get(choice);

    if (runnable != null) {
      runnable.run();
    } else {
      doAction();
    }

    CommandAction mainMenu = new MainMenuAction(new UserSelectionState());
    mainMenu.doAction();
  }

  private void searchByEmployeeNumber() {
    System.out.println("You selected Search by Employee Number");
    System.out.print("Enter Employee Number: ");
    Scanner scan = new Scanner(System.in);
    int sel = scan.nextInt();
    Optional<Employee> employeeOptional = employeeService.getEmployeeByEmployeeNumber(sel);
    if (employeeOptional.isPresent()) {
      DisplaySupplier.getDisplay().accept(Arrays.asList(employeeOptional.get()));
    } else {
      DisplaySupplier.getDisplay().accept(Collections.emptyList());
    }
  }

  private void searchByFirstName() {
    System.out.print("Enter Employee First Name: ");

    Scanner scanner = new Scanner(System.in);
    String firstName = scanner.nextLine();
    List<Employee> searchedEmployee =
        employeeService.getEmployeeByFirstName(firstName, SortEnum.defaultSort());

    DisplaySupplier.getDisplay().accept(searchedEmployee);
  }

  private void searchByLastName() {
    System.out.print("Enter Employee Last Name: ");

    Scanner scanner = new Scanner(System.in);
    String lastName = scanner.nextLine();
    List<Employee> searchedEmployee =
        employeeService.getEmployeeByLastName(lastName, SortEnum.defaultSort());

    DisplaySupplier.getDisplay().accept(searchedEmployee);
  }

  private void searchByMiddleName() {
    System.out.print("Enter Employee Middle Name: ");

    Scanner scanner = new Scanner(System.in);
    String middleName = scanner.nextLine();
    List<Employee> searchedEmployee =
        employeeService.getEmployeeByMiddleName(middleName, SortEnum.defaultSort());

    DisplaySupplier.getDisplay().accept(searchedEmployee);
  }

  private void searchByHiringDate() {
    System.out.print("Enter Date Hired: ");

    Scanner scanner = new Scanner(System.in);
    String hiringDate = scanner.nextLine();
    List<Employee> searchedEmployee =
        employeeService.getEmployeeByHiringDate(hiringDate, SortEnum.defaultSort());

    DisplaySupplier.getDisplay().accept(searchedEmployee);
  }
}
