package com.masterclass.employee.directory.menu;

import static com.masterclass.employee.directory.util.Constants.INSTRUCTION_ENTER_HIRING_DATE;
import static com.masterclass.employee.directory.util.Constants.INSTRUCTION_ENTER__NAME;
import static com.masterclass.employee.directory.util.Constants.INSTRUCTION_SELECT_AN_ACTION;
import static com.masterclass.employee.directory.util.Constants.OPTION_BACK;
import static com.masterclass.employee.directory.util.Constants.OPTION_HEADER_CHOOSE_AN_ACTION;
import static com.masterclass.employee.directory.util.Constants.OPTION_SEARCH_BY_EMPLOYEE_NUMBER;
import static com.masterclass.employee.directory.util.Constants.OPTION_SEARCH_BY_HIRING_DATE;
import static com.masterclass.employee.directory.util.Constants.OPTION_SEARCH_BY_NAME;

import com.masterclass.employee.directory.menu.display.DisplayEmployeesAction;
import com.masterclass.employee.directory.menu.option.Option;
import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import com.masterclass.employee.directory.util.InputHelper;
import com.masterclass.employee.directory.util.SortEnum;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Option(label = OPTION_SEARCH_BY_EMPLOYEE_NUMBER, key = 1)
@Option(label = OPTION_SEARCH_BY_NAME, key = 2)
@Option(label = OPTION_SEARCH_BY_HIRING_DATE, key = 3)
@Option(label = OPTION_BACK, key = -1)
public class SearchEmployeeAction implements CommandAction {

  private final Function<List<Employee>, CommandAction> displayEmployeesActionFunction =
      DisplayEmployeesAction::new;

  private final Map<Integer, Supplier<List<Employee>>> searchesMap;

  {
    searchesMap = new HashMap<>();
    searchesMap.put(1, this::searchByEmployeeNumber);
    searchesMap.put(2, this::searchByName);
    searchesMap.put(3, this::searchByHiringDate);
  }

  private EmployeeService employeeService = new EmployeeServiceImpl();
  private final UserSelectionState userSelectionState;

  public SearchEmployeeAction(UserSelectionState userSelectionState) {
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

    userSelectionState.getPreviousCommandActions().add(this);
    Supplier<List<Employee>> searchSupplier = searchesMap.get(selectedOption);

    displayEmployeesActionFunction.apply(searchSupplier.get()).doAction();

    userSelectionState.getPreviousCommandActions().pop().doAction();
  }

  private List<Employee> searchByEmployeeNumber() {
    int employeeNumber = InputHelper.askUserToProvideEmployeeNumber();

    Optional<Employee> employeeOptional =
        employeeService.getEmployeeByEmployeeNumber(employeeNumber);

    return employeeOptional.isPresent()
        ? Arrays.asList(employeeOptional.get())
        : Collections.emptyList();
  }

  private List<Employee> searchByName() {
    String name = InputHelper.askUserToProvideInput(INSTRUCTION_ENTER__NAME);

    return employeeService.getEmployeeByName(name, SortEnum.defaultSort());
  }

  private List<Employee> searchByHiringDate() {
    LocalDate hiringDate = InputHelper.askUserToProvideHiringDate(INSTRUCTION_ENTER_HIRING_DATE);

    return employeeService.getEmployeeByHiringDate(hiringDate, SortEnum.defaultSort());
  }
}
