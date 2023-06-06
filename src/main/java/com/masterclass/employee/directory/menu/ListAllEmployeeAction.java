package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;

import java.util.List;

public class ListAllEmployeeAction implements CommandAction {

  private EmployeeService employeeService = new EmployeeServiceImpl();

  private final UserSelectionState userSelectionState;

  public ListAllEmployeeAction(UserSelectionState userSelectionState) {
    this.userSelectionState = userSelectionState;
  }

  @Override
  public void doAction() {
    System.out.println("You selected List All Employee Records");

    CommandAction sortMenu = new SortMenuAction(userSelectionState);
    sortMenu.doAction();
    List<Employee> employees = employeeService.getAll(userSelectionState.getSortEnum());
    System.out.println("==========================================================================================");
    System.out.println("Employee Number                 Name                                       Date Hired");
    System.out.println("==========================================================================================");
    for (Employee emp: employees) {
      System.out.println(emp.getEmployeeNumber() + "\t\t\t\t\t\t" + emp.getFirstName() + " " + emp.getMiddleName() + " " + emp.getFirstName() + "\t\t\t\t\t\t\t" + emp.getHiringDate());
    }
    System.out.println("==========================================================================================");

    CommandAction mainMenu = new MainMenuAction(new UserSelectionState());
    mainMenu.doAction();
  }
}
