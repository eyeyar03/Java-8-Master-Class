package com.masterclass.employee.directory.menu;

import static com.masterclass.employee.directory.util.Constants.MESSAGE_EXIT;

public class ExitAction implements CommandAction {
  @Override
  public void doAction() {
    System.out.println(MESSAGE_EXIT);
    System.exit(0);
  }
}
