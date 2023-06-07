package com.masterclass.employee.directory.menu;

public class ExitAction implements CommandAction {
  @Override
  public void doAction() {
    System.out.println("Goodbye!");
    System.exit(0);
  }
}
