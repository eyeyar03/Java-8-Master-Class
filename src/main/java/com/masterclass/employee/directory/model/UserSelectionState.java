package com.masterclass.employee.directory.model;

import com.masterclass.employee.directory.menu.CommandAction;
import com.masterclass.employee.directory.util.OrderEnum;
import com.masterclass.employee.directory.util.SortEnum;
import java.util.Stack;
import lombok.Data;

@Data
public class UserSelectionState {

  private int addedEmployeeNumber;

  private OrderEnum orderEnum = OrderEnum.defaultOrder();

  private Stack<CommandAction> previousCommandActions = new Stack<>();

  private SortEnum sortEnum = SortEnum.defaultSort();

}
