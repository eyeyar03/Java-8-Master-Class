package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;

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

    employeeService.getAll(userSelectionState.getSortEnum()).forEach(System.out::println);

    CommandAction mainMenu = new MainMenuAction(new UserSelectionState());
    mainMenu.doAction();
  }
}
