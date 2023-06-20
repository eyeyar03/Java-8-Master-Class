package com.masterclass.employee.directory.util;

import static com.masterclass.employee.directory.util.Constants.DISPLAY_EMPLOYEE_FULL_NAME_FORMAT;
import static com.masterclass.employee.directory.util.Constants.DISPLAY_TABLE_BODY_FORMAT;
import static com.masterclass.employee.directory.util.Constants.DISPLAY_TABLE_HEADER_EMPLOYEE_NUMBER;
import static com.masterclass.employee.directory.util.Constants.DISPLAY_TABLE_HEADER_FORMAT;
import static com.masterclass.employee.directory.util.Constants.DISPLAY_TABLE_HEADER_HIRING_DATE;
import static com.masterclass.employee.directory.util.Constants.DISPLAY_TABLE__HEADER_EMPLOYEE_NAME;

import com.masterclass.employee.directory.model.Employee;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class EmployeeListOutputGenerator implements BiFunction<List<Employee>, Boolean, String> {

  @Override
  public String apply(List<Employee> employees, Boolean withHeader) {
    StringBuilder sb = new StringBuilder();

    String employeeDetailsList =
        employees.stream().map(this::generateEmployeeDetails).collect(Collectors.joining(""));

    if (withHeader) {
      sb.append(
          String.format(
              DISPLAY_TABLE_HEADER_FORMAT,
              DISPLAY_TABLE_HEADER_EMPLOYEE_NUMBER,
              DISPLAY_TABLE__HEADER_EMPLOYEE_NAME,
              DISPLAY_TABLE_HEADER_HIRING_DATE));
    }

    sb.append(employeeDetailsList);

    return sb.toString();
  }

  private String generateEmployeeDetails(Employee employee) {
    return String.format(
        DISPLAY_TABLE_BODY_FORMAT,
        employee.getEmployeeNumber(),
        String.format(
            DISPLAY_EMPLOYEE_FULL_NAME_FORMAT,
            employee.getFirstName(),
            employee.getMiddleName(),
            employee.getLastName()),
        employee.getHiringDate().format(DateTimeFormatterEnum.MMMsDDscYYYY.getDateTimeFormatter()));
  }
}
