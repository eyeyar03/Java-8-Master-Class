package com.masterclass.employee.directory.service;

import com.masterclass.employee.directory.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
  int addEmployee(Employee employee);

  Optional<Employee> getEmployeeByEmployeeNumber(int employeeNumber);

  List<Employee> getEmployeeByFirstName(String firstName);

  List<Employee> getEmployeeByLastName(String lastName);

  List<Employee> getEmployeeByMiddleName(String middleName);

  List<Employee> getAll();

  void deleteEmployeeByEmployeeNumber(int employeeNumber);
}
