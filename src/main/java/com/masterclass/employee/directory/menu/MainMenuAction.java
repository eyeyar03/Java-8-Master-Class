package com.masterclass.employee.directory.menu;

import static com.masterclass.employee.directory.util.Constants.*;

import com.masterclass.employee.directory.menu.option.Option;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.util.InputHelper;
import java.util.HashMap;
import java.util.Map;

@Option(label = OPTION_LIST_ALL_EMPLOYEE_RECORDS, key = 1)
@Option(label = OPTION_ADD_NEW_EMPLOYEE_RECORD, key = 2)
@Option(label = OPTION_DELETE_EMPLOYEE_RECORD, key = 3)
@Option(label = OPTION_SEARCH_EMPLOYEE_RECORD, key = 4)
@Option(label = OPTION_READ_FROM_FILE, key = 5)
@Option(label = OPTION_EXPORT_TO_FILE, key = 6)
@Option(label = OPTION_EXIT, key = -1)
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
    commandActionsMap.put(5, new ReadFromFileAction(userSelectionState));
    commandActionsMap.put(-1, new ExitAction());
  }

  @Override
  public void doAction() {
    userSelectionState.getPreviousCommandActions().add(this);

    int selectedOption =
        InputHelper.askUserToSelect(
            this.getClass(), OPTION_HEADER_MAIN_OPTIONS, INSTRUCTION_ENTER_ACTION_TYPE);

    CommandAction commandAction = commandActionsMap.get(selectedOption);
    commandAction.doAction();
  }
}
