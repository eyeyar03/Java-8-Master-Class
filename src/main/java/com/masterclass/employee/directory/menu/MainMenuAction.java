package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.menu.option.Option;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.util.OptionsHelper;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Option(label = "List All Employee Records", key = 1)
@Option(label = "Add New Employee Record", key = 2)
@Option(label = "Delete Employee Record", key = 3)
@Option(label = "Search Employee Record", key = 4)
@Option(label = "Exit", key = -1)
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

    OptionsHelper.printOptions(this.getClass(), "Main Options", "Enter action type: ");

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
