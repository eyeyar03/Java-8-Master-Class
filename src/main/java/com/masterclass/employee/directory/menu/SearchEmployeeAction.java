package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.model.UserSelectionState;

public class SearchEmployeeAction implements CommandAction {

  public SearchEmployeeAction(UserSelectionState userSelectionState) {}

    @Override
    public void doAction() {
        System.out.println("You selected Search Employee Record");
    }
}
