package com.masterclass.employee.directory.service;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.util.SearchEnum;
import com.masterclass.employee.directory.util.SortEnum;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
  int addEmployee(Employee employee);

  Optional<Employee> getEmployeeByEmployeeNumber(int employeeNumber);

  List<Employee> getEmployeeByFirstName(String firstName, SortEnum sortEnum);
  Optional<Employee> searchEmployeeByFirstName(String firstName);

  List<Employee> getEmployeeByLastName(String lastName, SortEnum sortEnum);

  Optional<Employee> searchEmployeeByLastName(String lastName);

  List<Employee> getEmployeeByMiddleName(String middleName, SortEnum sortEnum);

  Optional<Employee> searchEmployeeByMiddleName(String middleName);

  List<Employee> getEmployeeByHiringDate(String hiringDate, SortEnum sortEnum);

  Optional<Employee> searchEmployeeByHiringDate(String hiringDate);

  List<Employee> getAll(SortEnum sortEnum);

  Optional<Employee> deleteEmployeeByEmployeeNumber(int employeeNumber);

}
