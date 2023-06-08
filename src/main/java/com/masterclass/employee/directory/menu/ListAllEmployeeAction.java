package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.menu.display.DisplayEmployeesAction;
import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import java.util.List;
import java.util.function.Function;

public class ListAllEmployeeAction implements CommandAction {

  private final Function<List<Employee>, CommandAction> displayEmployeesActionFunction =
      DisplayEmployeesAction::new;

  private EmployeeService employeeService = new EmployeeServiceImpl();

  private CommandAction sortMenu;
  private final UserSelectionState userSelectionState;

  public ListAllEmployeeAction(UserSelectionState userSelectionState) {
    this.userSelectionState = userSelectionState;
    sortMenu = new SortMenuAction(userSelectionState);
  }

  @Override
  public void doAction() {
    sortMenu.doAction();

    List<Employee> employees = employeeService.getAll(userSelectionState.getSortEnum());

    displayEmployeesActionFunction.apply(employees).doAction();

    userSelectionState.getPreviousCommandActions().pop().doAction();
  }
}
