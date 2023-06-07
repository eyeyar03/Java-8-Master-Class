package com.masterclass.employee.directory.display;

import com.masterclass.employee.directory.model.Employee;
import java.util.List;
import java.util.function.Consumer;

public class DefaultDisplayForListingEmployees implements Consumer<List<Employee>> {

  @Override
  public void accept(List<Employee> employees) {
    String line = new String(new char[110]).replace('\0', '=');

    System.out.println(line);
    System.out.printf("| %-25s | %-50s | %-25s |\n", "Employee Number", "Name", "Date Hired");
    System.out.println(line);

    if (employees.isEmpty()) {
      displayNoRecordsFound();
    } else {
      employees.forEach(this::displayEmployee);
    }
    System.out.println(line);
  }

  private void displayEmployee(Employee employee) {
    System.out.printf(
        "| %-25s | %-50s | %-25s |\n",
        employee.getEmployeeNumber(),
        String.format(
            "%s %s %s", employee.getFirstName(), employee.getMiddleName(), employee.getLastName()),
        employee.getHiringDate());
  }

  private void displayNoRecordsFound() {
    System.out.printf("| %60s %-45s | \n", "No records found.", "");
  }
}
