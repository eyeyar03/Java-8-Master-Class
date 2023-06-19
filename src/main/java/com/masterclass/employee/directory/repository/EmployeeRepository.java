package com.masterclass.employee.directory.repository;

import com.masterclass.employee.directory.model.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

  private static List<Employee> employees = new ArrayList<>();

  public static int addEmployee(Employee employee) {
    employees.add(employee);

    return employee.getEmployeeNumber();
  }

  public static List<Employee> getEmployees() {
    return employees;
  }

  public static void setEmployees(List<Employee> employees) {
    EmployeeRepository.employees = employees;
  }

  public static void addAllEmployees(List<Employee> employees){
    employees.addAll(employees);
  }
}
