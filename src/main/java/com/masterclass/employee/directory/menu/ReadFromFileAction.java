package com.masterclass.employee.directory.menu;

import static com.masterclass.employee.directory.util.Constants.*;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import com.masterclass.employee.directory.util.DateTimeFormatterEnum;
import com.masterclass.employee.directory.util.InputHelper;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFromFileAction implements CommandAction {
  private EmployeeService employeeService = new EmployeeServiceImpl();

  private final UserSelectionState userSelectionState;

  public ReadFromFileAction(UserSelectionState userSelectionState) {
    this.userSelectionState = userSelectionState;
  }

  @Override
  public void doAction() {
    Optional<List<Employee>> optionalEmployees;

    do {
      optionalEmployees = parseEmployeeFromFile();
    } while (!optionalEmployees.isPresent());

    employeeService.addAllEmployee(optionalEmployees.get());

    userSelectionState.getPreviousCommandActions().pop().doAction();
  }

  private Optional<List<Employee>> parseEmployeeFromFile() {
    Optional<List<Employee>> optionalEmployees = Optional.empty();

    String fileName = InputHelper.askUserToProvideInput(INSTRUCTION_ENTER_FILENAME);

    try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
      List<Employee> employees =
          lines.skip(1).map(this::buildEmployee).collect(Collectors.toList());

      optionalEmployees = Optional.of(employees);

      System.out.printf(MESSAGE_RECORDS_SUCCESSFULLY_IMPORTED_FROM_FILE, fileName);
    } catch (Exception e) {
      System.out.printf(MESSAGE_ERROR_ENCOUNTERED_READING_FILE, fileName);
    }

    return optionalEmployees;
  }

  private Employee buildEmployee(String line) {
    String[] employeeDetails = line.split(",");
    String dateHired = line.substring(line.lastIndexOf(",") - 6).trim();

    return Employee.builder()
        .employeeNumber(Integer.parseInt(employeeDetails[0]))
        .firstName(employeeDetails[1])
        .middleName(employeeDetails[2])
        .lastName(employeeDetails[3])
        .hiringDate(getHiringDate(dateHired))
        .build();
  }

  private LocalDate getHiringDate(String date) {
    return LocalDate.parse(date, DateTimeFormatterEnum.MMMsDDscYYYY.getDateTimeFormatter());
  }
}
