package com.masterclass.employee.directory.menu;

public class ExitAction implements CommandAction {
  @Override
  public void doAction() {
    System.out.println("You selected to exit the program");
    System.exit(0);
  }
}
