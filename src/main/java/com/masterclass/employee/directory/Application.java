package com.masterclass.employee.directory;

import com.masterclass.employee.directory.menu.CommandAction;
import com.masterclass.employee.directory.menu.MainMenuAction;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.util.TestDataUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    TestDataUtil.generateTestData();

    CommandAction mainMenuAction = new MainMenuAction(new UserSelectionState());

    mainMenuAction.doAction();
  }
}
