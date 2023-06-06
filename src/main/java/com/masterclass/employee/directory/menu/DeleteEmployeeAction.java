package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.model.UserSelectionState;

public class DeleteEmployeeAction implements CommandAction {

  public DeleteEmployeeAction(UserSelectionState userSelectionState) {}

  @Override
  public void doAction() {
    System.out.println("You selected Delete Employee Record");
  }
}
