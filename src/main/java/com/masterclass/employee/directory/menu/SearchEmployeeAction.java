package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import com.masterclass.employee.directory.util.SearchEnum;
import com.masterclass.employee.directory.util.SortEnum;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SearchEmployeeAction implements CommandAction {

    private EmployeeService employeeService = new EmployeeServiceImpl();
    private final UserSelectionState userSelectionState;
  public SearchEmployeeAction(UserSelectionState userSelectionState) {
      this.userSelectionState = userSelectionState;
  }

    @Override

    public void doAction() {
        System.out.println("You selected Search Employee Record");
        System.out.println("Choose an action");
        System.out.println("[1] Search by Employee Number");
        System.out.println("[2] Search by First Name");
        System.out.println("[3] Search by Middle Name");
        System.out.println("[4] Search by Last Name");
        System.out.println("[5] Search by Hiring Date");
        System.out.println("[-1] back\n");
        System.out.print("Select action: ");

        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();

        Consumer<Integer> option = selectSearchActionMenu(choice);
        if (option != null) {
            option.accept(choice);
        } else {
            doAction();
        }
    }
    public Consumer<Integer> selectSearchActionMenu(int choice) {
        switch (choice){
            case 1:
                return (selected) -> {
                    System.out.println("You selected Search by Employee Number");
                    System.out.print("Enter Employee Number: ");
                    Scanner scan = new Scanner(System.in);
                    int sel = scan.nextInt();
                    Optional<Employee> employeeOptional = employeeService.getEmployeeByEmployeeNumber(sel);
                    if (employeeOptional.isPresent()) {
                        System.out.println(employeeOptional.get());
                        doAction();
                    }else {
                        System.out.println("Employee not found!\n");
                        doAction();
                    }
                };
            case 2:
                return (selected) -> {
                    System.out.println("You selected Search by Firstname");
                    System.out.print("Enter Employee Firstname: ");
                    Scanner scan = new Scanner(System.in);
                    String firstname = scan.nextLine();
                    List<Employee> searchedEmployee = employeeService.getEmployeeByFirstName(firstname, SortEnum.BY_FIRST_NAME);
                };
            case 3:
                return (selected) -> {
                    System.out.println("You selected Search by Middle Name");
                };
            case 4:
                return (selected) -> {
                    System.out.println("You selected Search by Last Name");
                };
            case 5:
                return (selected) -> {
                    System.out.println("You selected Sorted by Hiring Date");
                };
            case -1:
                return (selected) -> {
                    userSelectionState.getPreviousCommandActions().pop().doAction();
                };
            default:
                return (selected) -> {
                    System.out.println("Invalid Input\n");
                    doAction();
                };
        }
    }
}
