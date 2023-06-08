package com.masterclass.employee.directory.menu;

import static com.masterclass.employee.directory.util.Constants.DISPLAY_EMPLOYEE_FULL_NAME_FORMAT;
import static com.masterclass.employee.directory.util.Constants.DISPLAY_LINE;
import static com.masterclass.employee.directory.util.Constants.DISPLAY_NO_RECORDS_FOUND;
import static com.masterclass.employee.directory.util.Constants.DISPLAY_NO_RECORDS_FOUND_FORMAT;
import static com.masterclass.employee.directory.util.Constants.DISPLAY_TABLE_BODY_FORMAT;
import static com.masterclass.employee.directory.util.Constants.DISPLAY_TABLE_HEADER_EMPLOYEE_NUMBER;
import static com.masterclass.employee.directory.util.Constants.DISPLAY_TABLE_HEADER_FORMAT;
import static com.masterclass.employee.directory.util.Constants.DISPLAY_TABLE_HEADER_HIRING_DATE;
import static com.masterclass.employee.directory.util.Constants.DISPLAY_TABLE_HEADER_NAME;
import static com.masterclass.employee.directory.util.Constants.EMPTY_STRING;

import com.masterclass.employee.directory.model.Employee;
import java.util.List;

public class DisplayEmployeesAction implements CommandAction {

  private final List<Employee> employees;

  public DisplayEmployeesAction(List<Employee> employees) {
    this.employees = employees;
  }

  @Override
  public void doAction() {
    System.out.println(DISPLAY_LINE);
    System.out.printf(
        DISPLAY_TABLE_HEADER_FORMAT,
        DISPLAY_TABLE_HEADER_EMPLOYEE_NUMBER,
        DISPLAY_TABLE_HEADER_NAME,
        DISPLAY_TABLE_HEADER_HIRING_DATE);
    System.out.println(DISPLAY_LINE);

    if (employees.isEmpty()) {
      displayNoRecordsFound();
    } else {
      employees.forEach(this::displayEmployee);
    }
    System.out.println(DISPLAY_LINE);
  }

  private void displayEmployee(Employee employee) {
    System.out.printf(
        DISPLAY_TABLE_BODY_FORMAT,
        employee.getEmployeeNumber(),
        String.format(
            DISPLAY_EMPLOYEE_FULL_NAME_FORMAT,
            employee.getFirstName(),
            employee.getMiddleName(),
            employee.getLastName()),
        employee.getHiringDate());
  }

  private void displayNoRecordsFound() {
    System.out.printf(DISPLAY_NO_RECORDS_FOUND_FORMAT, DISPLAY_NO_RECORDS_FOUND, EMPTY_STRING);
  }
}
