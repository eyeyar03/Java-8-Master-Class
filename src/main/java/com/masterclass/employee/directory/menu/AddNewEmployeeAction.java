package com.masterclass.employee.directory.menu;

import static com.masterclass.employee.directory.util.Constants.INSTRUCTION_FIRST_NAME;
import static com.masterclass.employee.directory.util.Constants.INSTRUCTION_HIRING_DATE;
import static com.masterclass.employee.directory.util.Constants.INSTRUCTION_LAST_NAME;
import static com.masterclass.employee.directory.util.Constants.INSTRUCTION_MIDDLE_NAME;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import com.masterclass.employee.directory.util.InputHelper;
import java.util.function.Function;

public class AddNewEmployeeAction implements CommandAction {

  private final Function<Employee, CommandAction> displayNewlyAddedEmployeeActionFunction =
      DisplayNewlyAddedEmployeeAction::new;

  private EmployeeService employeeService = new EmployeeServiceImpl();

  private final UserSelectionState userSelectionState;

  public AddNewEmployeeAction(UserSelectionState userSelectionState) {
    this.userSelectionState = userSelectionState;
  }

  @Override
  public void doAction() {
    int employeeNumber = InputHelper.askUserToProvideEmployeeNumber();

    String firstName = InputHelper.askUserToProvideInput(INSTRUCTION_FIRST_NAME);
    String lastName = InputHelper.askUserToProvideInput(INSTRUCTION_LAST_NAME);
    String middleName = InputHelper.askUserToProvideInput(INSTRUCTION_MIDDLE_NAME);
    String hiringDate = InputHelper.askUserToProvideInput(INSTRUCTION_HIRING_DATE);

    Employee employee =
        Employee.builder()
            .employeeNumber(employeeNumber)
            .firstName(firstName)
            .lastName(lastName)
            .middleName(middleName)
            .hiringDate(hiringDate)
            .build();

    employeeService.addEmployee(employee);

    displayNewlyAddedEmployeeActionFunction.apply(employee).doAction();

    userSelectionState.getPreviousCommandActions().pop().doAction();
  }
}
