package com.masterclass.employee.directory.menu;

import static com.masterclass.employee.directory.util.Constants.INSTRUCTION_ENTER_FILENAME;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import com.masterclass.employee.directory.util.EmployeeListOutputGenerator;
import com.masterclass.employee.directory.util.InputHelper;
import com.masterclass.employee.directory.util.SortEnum;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.function.BiFunction;

public class ExportToFileAction implements CommandAction {

  private final BiFunction<List<Employee>, Boolean, String> employeeListOutputGenerator =
      new EmployeeListOutputGenerator();

  private final EmployeeService employeeService = new EmployeeServiceImpl();

  private final UserSelectionState userSelectionState;

  public ExportToFileAction(UserSelectionState userSelectionState) {
    this.userSelectionState = userSelectionState;
  }

  @Override
  public void doAction() {
    List<Employee> employeeList = employeeService.getAll(SortEnum.defaultSort().getComparator());

    String fileName = InputHelper.askUserToProvideInput(INSTRUCTION_ENTER_FILENAME);
    Path path = Paths.get(fileName);

    export(path, employeeList);

    userSelectionState.getPreviousCommandActions().pop().doAction();
  }

  private void export(Path path, List<Employee> employeeList) {
    try {
      if (Files.exists(path)) {
        Files.write(
            path,
            employeeListOutputGenerator.apply(employeeList, false).getBytes(),
            StandardOpenOption.APPEND);
      } else {
        Files.write(path, employeeListOutputGenerator.apply(employeeList, true).getBytes());
      }

      System.out.printf("Records exported successfully to file %s.\n\n", path);
    } catch (Exception e) {
      System.out.println("Error encountered exporting to a file.");
    }
  }
}
