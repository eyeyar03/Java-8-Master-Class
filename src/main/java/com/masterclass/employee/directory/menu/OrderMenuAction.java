package com.masterclass.employee.directory.menu;

import static com.masterclass.employee.directory.util.Constants.INSTRUCTION_SELECT_AN_ACTION;
import static com.masterclass.employee.directory.util.Constants.OPTION_BACK;
import static com.masterclass.employee.directory.util.Constants.OPTION_HEADER_CHOOSE_AN_ACTION;
import static com.masterclass.employee.directory.util.Constants.OPTION_ORDER_BY_ASC;
import static com.masterclass.employee.directory.util.Constants.OPTION_ORDER_BY_DESC;

import com.masterclass.employee.directory.menu.option.Option;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.util.InputHelper;
import com.masterclass.employee.directory.util.OrderEnum;
import java.util.HashMap;
import java.util.Map;

@Option(label = OPTION_ORDER_BY_ASC, key = 1)
@Option(label = OPTION_ORDER_BY_DESC, key = 2)
@Option(label = OPTION_BACK, key = -1)
public class OrderMenuAction implements CommandAction {

  private static final Map<Integer, OrderEnum> ORDER_ENUMS_MAP;

  static {
    ORDER_ENUMS_MAP = new HashMap<>();
    ORDER_ENUMS_MAP.put(1, OrderEnum.ASC);
    ORDER_ENUMS_MAP.put(2, OrderEnum.DESC);
  }

  private final UserSelectionState userSelectionState;

  public OrderMenuAction(UserSelectionState userSelectionState) {
    this.userSelectionState = userSelectionState;
  }

  @Override
  public void doAction() {
    int selectedOption =
        InputHelper.askUserToSelect(
            this.getClass(), OPTION_HEADER_CHOOSE_AN_ACTION, INSTRUCTION_SELECT_AN_ACTION);

    if (selectedOption == -1) {
      userSelectionState.getPreviousCommandActions().pop().doAction();
      return;
    }

    userSelectionState.setOrderEnum(ORDER_ENUMS_MAP.get(selectedOption));
    userSelectionState.getPreviousCommandActions().pop();
  }
}
