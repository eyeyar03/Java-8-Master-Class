package com.masterclass.employee.directory.serviceimplementation;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.repository.EmployeeRepository;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.util.SearchEnum;
import com.masterclass.employee.directory.util.SortEnum;
import java.util.List;
import java.util.Objects;
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
  public List<Employee> getEmployeeByFirstName(String firstName, SortEnum sortEnum) {
    return getMatchedEmployees(e -> e.getFirstName() == firstName, sortEnum);
  }

  @Override
  public Optional<Employee> searchEmployeeByFirstName(String firstName) {
    return EmployeeRepository.getEmployees().stream().filter(e -> Objects.equals(e.getFirstName(), firstName)).findAny();
  }

  @Override
  public List<Employee> getEmployeeByLastName(String lastName, SortEnum sortEnum) {
    return getMatchedEmployees(e -> e.getLastName() == lastName, sortEnum);
  }

  @Override
  public Optional<Employee> searchEmployeeByLastName(String lastName) {
    return EmployeeRepository.getEmployees().stream().filter(e -> Objects.equals(e.getLastName(), lastName)).findFirst();
  }

  @Override
  public List<Employee> getEmployeeByMiddleName(String middleName, SortEnum sortEnum) {
    return getMatchedEmployees(e -> e.getMiddleName() == middleName, sortEnum);
  }

  @Override
  public Optional<Employee> searchEmployeeByMiddleName(String middleName) {
    return EmployeeRepository.getEmployees().stream().filter(e -> Objects.equals(e.getMiddleName(), middleName)).findFirst();
  }

  @Override
  public List<Employee> getEmployeeByHiringDate(String hiringDate, SortEnum sortEnum) {
    return getMatchedEmployees(e -> e.getHiringDate() == hiringDate, sortEnum);
  }

  @Override
  public Optional<Employee> searchEmployeeByHiringDate(String hiringDate) {
    return EmployeeRepository.getEmployees().stream().filter(e -> Objects.equals(e.getHiringDate(), hiringDate)).findFirst();
  }

  @Override
  public List<Employee> getAll(SortEnum sortEnum) {
    return EmployeeRepository.getEmployees().stream()
        .sorted(sortEnum.getComparator())
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Employee> deleteEmployeeByEmployeeNumber(int employeeNumber) {
    EmployeeRepository.setEmployees(
        getMatchedEmployees(e -> e.getEmployeeNumber() != employeeNumber, SortEnum.defaultSort()));
    return Optional.empty();
  }

  private List<Employee> getMatchedEmployees(
      Predicate<Employee> employeePredicate, SortEnum sortEnum) {
    return EmployeeRepository.getEmployees().stream()
        .filter(employeePredicate)
        .sorted(sortEnum.getComparator())
        .collect(Collectors.toList());
  }
}
