package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.display.DisplaySupplier;
import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import java.util.List;

public class ListAllEmployeeAction implements CommandAction {

  private EmployeeService employeeService = new EmployeeServiceImpl();

  private CommandAction sortMenu;
  private final UserSelectionState userSelectionState;

  public ListAllEmployeeAction(UserSelectionState userSelectionState) {
    this.userSelectionState = userSelectionState;
    sortMenu = new SortMenuAction(userSelectionState);
  }

  @Override
  public void doAction() {
    System.out.println("You selected List All Employee Records");
    sortMenu.doAction();

    List<Employee> employees = employeeService.getAll(userSelectionState.getSortEnum());

    DisplaySupplier.getDefaultDisplayForListingEmployees().accept(employees);

    userSelectionState.getPreviousCommandActions().pop().doAction();
  }
}
