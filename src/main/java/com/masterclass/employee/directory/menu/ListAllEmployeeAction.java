package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import com.masterclass.employee.directory.util.TextBlocks;

public class ListAllEmployeeAction implements CommandAction {

  TextBlocks tb = new TextBlocks();
  private EmployeeService employeeService = new EmployeeServiceImpl();

  private final UserSelectionState userSelectionState;

  public ListAllEmployeeAction(UserSelectionState userSelectionState) {
    this.userSelectionState = userSelectionState;
  }

  @Override
  public void doAction() {
    System.out.println("You selected List All Employee Records");
    CommandAction sortMenu = new SortMenuAction(userSelectionState);
    CommandAction mainMenu = new MainMenuAction(new UserSelectionState());
    boolean runMenu = true;
    int choice = 0;

//    CommandAction sortMenu = new SortMenuAction(userSelectionState);
//    sortMenu.doAction();
//    System.out.println(tb.border());
//    System.out.println(tb.infoDisplay());
//    System.out.println(tb.border());
//    employeeService.getAll(userSelectionState.getSortEnum()).forEach(System.out::println);
//    System.out.println(tb.border());

//    CommandAction mainMenu = new MainMenuAction(new UserSelectionState());
//    mainMenu.doAction();

    // should return to sorting menu not on main menu right away
    //While loop?

    while (runMenu){
      sortMenu.doAction();
      System.out.println(tb.border());
      System.out.println(tb.infoDisplay());
      System.out.println(tb.border());
      employeeService.getAll(userSelectionState.getSortEnum()).forEach(System.out::println);
      System.out.println(tb.border());

      if (choice == -1){
        runMenu = false;
        mainMenu.doAction();
      }
    }

  }
}
