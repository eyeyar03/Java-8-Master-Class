package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.util.SortEnum;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
    System.out.println("Choose an action");
    System.out.println("[1] Sorted by Employee Number");
    System.out.println("[2] Sorted by First Name");
    System.out.println("[3] Sorted by Last Name");
    System.out.println("[4] Sorted by Hiring Date");
    System.out.println("[-1] back\n");
    System.out.print("Select action: ");

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
