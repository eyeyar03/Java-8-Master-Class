package com.masterclass.employee.directory.serviceimplementation;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.repository.EmployeeRepository;
import com.masterclass.employee.directory.service.EmployeeService;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

  @Override
  public int addEmployee(Employee employee) {
    return EmployeeRepository.addEmployee(employee);
  }

  @Override
  public Optional<Employee> getEmployeeByEmployeeNumber(int employeeNumber) {
    return EmployeeRepository.getEmployees().stream()
        .filter(e -> e.getEmployeeNumber() == employeeNumber)
        .findFirst();
  }

  @Override
  public List<Employee> getEmployeeByFirstName(String firstName) {
    return getMatchedEmployees(e -> e.getFirstName() == firstName);
  }

  @Override
  public List<Employee> getEmployeeByLastName(String lastName) {
    return getMatchedEmployees(e -> e.getLastName() == lastName);
  }

  @Override
  public List<Employee> getEmployeeByMiddleName(String middleName) {
    return getMatchedEmployees(e -> e.getMiddleName() == middleName);
  }

  @Override
  public List<Employee> getAll() {
    return EmployeeRepository.getEmployees();
  }

  @Override
  public void deleteEmployeeByEmployeeNumber(int employeeNumber) {
    EmployeeRepository.setEmployees(
        getMatchedEmployees(e -> e.getEmployeeNumber() != employeeNumber));
  }

  private List<Employee> getMatchedEmployees(Predicate<Employee> employeePredicate) {
    return EmployeeRepository.getEmployees().stream()
        .filter(employeePredicate)
        .collect(Collectors.toList());
  }
}
