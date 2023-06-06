package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import com.masterclass.employee.directory.util.SearchEnum;
import com.masterclass.employee.directory.util.SortEnum;
import com.masterclass.employee.directory.util.TextBlocks;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SearchEmployeeAction implements CommandAction {

    private EmployeeService employeeService = new EmployeeServiceImpl();
    private final UserSelectionState userSelectionState;

    TextBlocks tb = new TextBlocks();
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
        Scanner scan = new Scanner(System.in);

            switch (choice) {
                case 1:
                    return (selected) -> {
                        System.out.println("You selected Search by Employee Number");
                        System.out.print("Enter Employee Number: ");
                        int sel = scan.nextInt();
                        Optional<Employee> employeeOptional = employeeService.getEmployeeByEmployeeNumber(sel);
                        if (employeeOptional.isPresent()) {
                            System.out.println(tb.border());
                            System.out.println(tb.infoDisplay());
                            System.out.println(tb.border());
                            System.out.println(employeeOptional.get());
                            System.out.println(tb.border());
                            doAction();
                        } else {
                            System.out.println("Employee not found!\n");
                            doAction();
                        }
                    };
                case 2:
                    return (selected) -> {
                        System.out.println("You selected Search by Firstname");
                        System.out.print("Enter Employee Firstname: ");
                        String firstname = scan.nextLine();
                        Optional<Employee> employeeFirstNameOptional = employeeService.searchEmployeeByFirstName(firstname);
                        if (employeeFirstNameOptional.isPresent()) {
                            System.out.println(tb.border());
                            System.out.println(tb.infoDisplay());
                            System.out.println(tb.border());
                            System.out.println(employeeFirstNameOptional.get());
                            System.out.println(tb.border());
                            doAction();
                        }else{
                            System.out.println("Employee not found!\n");
                            doAction();
                        }
                    };
                case 3:
                    return (selected) -> {
                        System.out.println("You selected Search by Middle Name");
                        System.out.print("Enter Employee Middlename: ");
                        String middleName = scan.nextLine();
                        Optional<Employee> employeeMiddleNameOptional = employeeService.searchEmployeeByMiddleName(middleName);
                        if (employeeMiddleNameOptional.isPresent()) {
                            System.out.println(tb.border());
                            System.out.println(tb.infoDisplay());
                            System.out.println(tb.border());
                            System.out.println(employeeMiddleNameOptional.get());
                            System.out.println(tb.border());
                            doAction();
                        }else{
                            System.out.println("Employee not found!\n");
                            doAction();
                        }
                    };
                case 4:
                    return (selected) -> {
                        System.out.println("You selected Search by Last Name");
                        System.out.print("Enter Employee Last Name: ");
                        String lastName = scan.nextLine();
                        Optional<Employee> employeeByLastNameNameOptional = employeeService.searchEmployeeByLastName(lastName);
                        if (employeeByLastNameNameOptional.isPresent()) {
                            System.out.println(tb.border());
                            System.out.println(tb.infoDisplay());
                            System.out.println(tb.border());
                            System.out.println(employeeByLastNameNameOptional.get());
                            System.out.println(tb.border());
                            doAction();
                        }else{
                            System.out.println("Employee not found!\n");
                            doAction();
                        }
                    };
                case 5:
                    return (selected) -> {

                        System.out.println("You selected Sorted by Hiring Date");
                        System.out.print("Enter Employee Hired Date: ");
                        String hiredDate = scan.nextLine();
                        Optional<Employee> employeeHiredDateOptional = employeeService.searchEmployeeByHiringDate(hiredDate);
                        if (employeeHiredDateOptional.isPresent()) {
                            System.out.println(tb.border());
                            System.out.println(tb.infoDisplay());
                            System.out.println(tb.border());
                            System.out.println(employeeHiredDateOptional.get());
                            System.out.println(tb.border());
                            doAction();
                        }else{
                            System.out.println("Employee not found!\n");
                            doAction();
                        }
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
