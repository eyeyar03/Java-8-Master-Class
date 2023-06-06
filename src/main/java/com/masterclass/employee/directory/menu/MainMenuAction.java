package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.model.UserSelectionState;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenuAction implements CommandAction {

  private Map<Integer, CommandAction> commandActionsMap;

  private final UserSelectionState userSelectionState;

  public MainMenuAction(UserSelectionState userSelectionState) {
    this.userSelectionState = userSelectionState;

    commandActionsMap = new HashMap<>();
    commandActionsMap.put(1, new ListAllEmployeeAction(userSelectionState));
    commandActionsMap.put(2, new AddNewEmployeeAction(userSelectionState));
    commandActionsMap.put(3, new DeleteEmployeeAction(userSelectionState));
    commandActionsMap.put(4, new SearchEmployeeAction(userSelectionState));
    commandActionsMap.put(-1, new ExitAction());
  }

  @Override
  public void doAction() {
    userSelectionState.getPreviousCommandActions().add(this);

    System.out.println("Main Option");
    System.out.println("[1] List All Employee Records");
    System.out.println("[2] Add New Employee Record");
    System.out.println("[3] Delete Employee Record");
    System.out.println("[4] Search Employee Record");
    System.out.println("[-1] exit\n");
    System.out.print("Enter action type: ");

    Scanner scan = new Scanner(System.in);
    int choice = scan.nextInt();

    CommandAction commandAction = commandActionsMap.get(choice);

    if (commandAction != null) {
      commandAction.doAction();
    } else {
      doAction();
    }
  }
}
