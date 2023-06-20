package com.masterclass.employee.directory.menu;

import static com.masterclass.employee.directory.util.Constants.INSTRUCTION_ENTER_ACTION_TYPE;
import static com.masterclass.employee.directory.util.Constants.INSTRUCTION_ENTER_FILENAME;
import static com.masterclass.employee.directory.util.Constants.OPTION_EXPORT_AS_ENCODED;
import static com.masterclass.employee.directory.util.Constants.OPTION_EXPORT_AS_NOT_ENCODED;
import static com.masterclass.employee.directory.util.Constants.OPTION_HEADER_EXPORT_OPTIONS;

import com.masterclass.employee.directory.menu.option.Option;
import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import com.masterclass.employee.directory.util.EmployeeListEncodedOutputGenerator;
import com.masterclass.employee.directory.util.EmployeeListOutputGenerator;
import com.masterclass.employee.directory.util.InputHelper;
import com.masterclass.employee.directory.util.SortEnum;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@Option(label = OPTION_EXPORT_AS_ENCODED, key = 1)
@Option(label = OPTION_EXPORT_AS_NOT_ENCODED, key = 2)
public class ExportToFileAction implements CommandAction {

  private final Map<Integer, BiConsumer<Path, List<Employee>>> EXPORTERS_MAP;

  {
    EXPORTERS_MAP = new HashMap();
    EXPORTERS_MAP.put(1, this::exportAsEncoded);
    EXPORTERS_MAP.put(2, this::exportAsNonEncoded);
  }

  private final BiFunction<List<Employee>, Boolean, String> employeeListOutputGenerator =
      new EmployeeListOutputGenerator();

  private final Function<List<Employee>, String> employeeListEncodedOutputGenerator =
      new EmployeeListEncodedOutputGenerator();

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

    int selectedOption =
        InputHelper.askUserToSelect(
            this.getClass(), OPTION_HEADER_EXPORT_OPTIONS, INSTRUCTION_ENTER_ACTION_TYPE);

    EXPORTERS_MAP.get(selectedOption).accept(path, employeeList);

    userSelectionState.getPreviousCommandActions().pop().doAction();
  }

  private void exportAsNonEncoded(Path path, List<Employee> employeeList) {
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

  private void exportAsEncoded(Path path, List<Employee> employeeList) {
    try {
      String encodedEmployeeDetails = employeeListEncodedOutputGenerator.apply(employeeList);

      if (Files.exists(path)) {
        Files.write(
            path,
            String.format("\n%s", encodedEmployeeDetails).getBytes(),
            StandardOpenOption.APPEND);
      } else {
        Files.write(path, encodedEmployeeDetails.getBytes());
      }

      System.out.printf("Records exported successfully to file %s.\n\n", path);
    } catch (Exception e) {
      System.out.println("Error encountered exporting to a file.");
    }
  }
}
