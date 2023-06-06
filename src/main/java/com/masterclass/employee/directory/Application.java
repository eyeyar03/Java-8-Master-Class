package com.masterclass.employee.directory;

import com.masterclass.employee.directory.menu.CommandAction;
import com.masterclass.employee.directory.menu.MainMenuAction;
import com.masterclass.employee.directory.model.UserSelectionState;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    UserSelectionState userSelectionState = new UserSelectionState();

    CommandAction MAIN_MENU = new MainMenuAction(userSelectionState);

    MAIN_MENU.doAction();
  }
}
