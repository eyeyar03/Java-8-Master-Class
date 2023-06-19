package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.menu.display.DisplayEmployeesAction;
import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import com.masterclass.employee.directory.util.OrderEnum;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class ListAllEmployeeAction implements CommandAction {

  private final Function<List<Employee>, CommandAction> displayEmployeesActionFunction =
      DisplayEmployeesAction::new;

  private EmployeeService employeeService = new EmployeeServiceImpl();

  private final CommandAction sortMenu;
  private final UserSelectionState userSelectionState;

  public ListAllEmployeeAction(UserSelectionState userSelectionState) {
    this.userSelectionState = userSelectionState;
    sortMenu = new SortMenuAction(userSelectionState);
  }

  @Override
  public void doAction() {
    sortMenu.doAction();

    List<Employee> employees = employeeService.getAll(buildComparator());

    displayEmployeesActionFunction.apply(employees).doAction();

    userSelectionState.getPreviousCommandActions().pop().doAction();
  }

  private Comparator<Employee> buildComparator() {
    Comparator<Employee> employeeComparator = userSelectionState.getSortEnum().getComparator();
    if (userSelectionState.getOrderEnum() == OrderEnum.ASC) {
      return employeeComparator;
    } else {
      return employeeComparator.reversed();
    }
  }
}
