package com.masterclass.employee.directory.util;

import static com.masterclass.employee.directory.util.Constants.DISPLAY_EMPLOYEE_FULL_NAME_FORMAT;
import static com.masterclass.employee.directory.util.Constants.DISPLAY_TABLE_BODY_FORMAT;

import com.masterclass.employee.directory.model.Employee;
import java.util.Base64;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeeListEncodedOutputGenerator implements Function<List<Employee>, String> {

  @Override
  public String apply(List<Employee> employees) {
    return employees.stream()
        .map(this::generateEmployeeDetails)
        .map(String::getBytes)
        .map(Base64.getEncoder()::encodeToString)
        .collect(Collectors.joining("\n"));
  }

  private String generateEmployeeDetails(Employee employee) {
    String employeeDetails =
        String.format(
            DISPLAY_TABLE_BODY_FORMAT,
            employee.getEmployeeNumber(),
            String.format(
                DISPLAY_EMPLOYEE_FULL_NAME_FORMAT,
                employee.getFirstName(),
                employee.getMiddleName(),
                employee.getLastName()),
            employee
                .getHiringDate()
                .format(DateTimeFormatterEnum.MMMsDDscYYYY.getDateTimeFormatter()));

    return employeeDetails;
  }
}
