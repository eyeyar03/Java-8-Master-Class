package com.masterclass.employee.directory.service;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.util.SortEnum;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
  int addEmployee(Employee employee);

  Optional<Employee> getEmployeeByEmployeeNumber(int employeeNumber);

  List<Employee> getEmployeeByFirstName(String firstName, SortEnum sortEnum);

  List<Employee> getEmployeeByLastName(String lastName, SortEnum sortEnum);

  List<Employee> getEmployeeByMiddleName(String middleName, SortEnum sortEnum);

  List<Employee> getEmployeeByName(String name, SortEnum sortEnum);

  List<Employee> getEmployeeByHiringDate(LocalDate hiringDate, SortEnum sortEnum);

  List<Employee> getAll(Comparator<Employee> employeeComparator);

  Optional<Employee> deleteEmployeeByEmployeeNumber(int employeeNumber);

  void addAllEmployee(List<Employee> employees);
}
