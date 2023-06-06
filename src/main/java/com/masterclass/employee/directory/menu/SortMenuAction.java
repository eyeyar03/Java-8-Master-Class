package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.menu.option.Option;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.util.OptionsHelper;
import com.masterclass.employee.directory.util.SortEnum;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Option(label = "Sorted by Employee Number", key = 1)
@Option(label = "Sorted by First Name", key = 2)
@Option(label = "Sorted by Last Name", key = 3)
@Option(label = "Sorted by Hiring Date", key = 4)
@Option(label = "Back", key = -1)
public class SortMenuAction implements CommandAction {

  private static final Map<Integer, SortEnum> SORT_ENUMS_MAP;

  static {
    SORT_ENUMS_MAP = new HashMap<>();
    SORT_ENUMS_MAP.put(1, SortEnum.BY_EMPLOYEE_NUMBER);
    SORT_ENUMS_MAP.put(2, SortEnum.BY_FIRST_NAME);
    SORT_ENUMS_MAP.put(3, SortEnum.BY_LAST_NAME);
    SORT_ENUMS_MAP.put(4, SortEnum.BY_HIRING_DATE);
  }

  private final UserSelectionState userSelectionState;

  public SortMenuAction(UserSelectionState userSelectionState) {
    this.userSelectionState = userSelectionState;
  }

  @Override
  public void doAction() {
    OptionsHelper.printOptions(this.getClass(), "Choose an action", "Select action: ");

    Scanner scan = new Scanner(System.in);
    int choice = scan.nextInt();

    if (choice == -1) {
      userSelectionState.getPreviousCommandActions().pop().doAction();
      return;
    }

    SortEnum sortEnum = SORT_ENUMS_MAP.get(choice);

    if (sortEnum != null) {
      userSelectionState.setSortEnum(sortEnum);

    } else {
      doAction();
    }
  }
}
