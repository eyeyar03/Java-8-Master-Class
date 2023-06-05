package com.masterclass.employee.directory.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.repository.EmployeeRepository;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeServiceTest {
  private EmployeeService employeeService = new EmployeeServiceImpl();

  @BeforeEach
  void setup() {
    addEmployees();
  }

  @Test
  void shouldAddEmployee() {
    Employee employeeToBeAdded =
        Employee.builder()
            .employeeNumber(12346)
            .firstName("Bruce")
            .hiringDate("2023-06-07")
            .lastName("Wayne")
            .middleName("B")
            .build();

    int employeeNumber = employeeService.addEmployee(employeeToBeAdded);

    Optional<Employee> optionalEmployee =
        employeeService.getEmployeeByEmployeeNumber(employeeNumber);

    assertEmployee(employeeToBeAdded, optionalEmployee);
  }

  @Test
  void getEmployeeByEmployeeNumberShouldNotReturnEmployee() {
    Optional<Employee> optionalEmployee = employeeService.getEmployeeByEmployeeNumber(12987);

    assertFalse(optionalEmployee.isPresent());
  }

  @Test
  void getEmployeeByEmployeeNumberShouldReturnEmployee() {
    Employee expectedEmployee =
        Employee.builder()
            .employeeNumber(12345)
            .firstName("Peter")
            .hiringDate("2023-06-06")
            .lastName("Parker")
            .middleName("S")
            .build();

    Optional<Employee> optionalEmployee = employeeService.getEmployeeByEmployeeNumber(12345);

    assertEmployee(expectedEmployee, optionalEmployee);
  }

  @Test
  void getEmployeeByFirstNameShouldReturnNoEmployees() {
    List<Employee> employees = employeeService.getEmployeeByFirstName("Spidey");

    assertTrue(employees.isEmpty());
  }

  @Test
  void getEmployeeByFirstNameShouldReturnEmployees() {
    Map<Integer, Employee> expectedEmployeesMap = new HashMap<>();
    expectedEmployeesMap.put(
        12346,
        Employee.builder()
            .employeeNumber(12346)
            .firstName("Bruce")
            .hiringDate("2023-06-07")
            .lastName("Wayne")
            .middleName("B")
            .build());

    expectedEmployeesMap.put(
        12351,
        Employee.builder()
            .employeeNumber(12351)
            .firstName("Bruce")
            .hiringDate("2023-06-11")
            .lastName("Banner")
            .middleName("H")
            .build());

    List<Employee> employees = employeeService.getEmployeeByFirstName("Bruce");

    assertFalse(employees.isEmpty());
    assertEquals(2, employees.size());

    employees.forEach(
        e ->
            assertEmployee(
                expectedEmployeesMap.get(e.getEmployeeNumber()), Optional.ofNullable(e)));
  }

  @Test
  void getEmployeeByLastNameShouldReturnNoEmployees() {
    List<Employee> employees = employeeService.getEmployeeByLastName("Spidey");

    assertTrue(employees.isEmpty());
  }

  @Test
  void getEmployeeByLastNameShouldReturnEmployees() {
    Employee expectedEmployee =
        Employee.builder()
            .employeeNumber(12348)
            .firstName("Tony")
            .hiringDate("2023-06-08")
            .lastName("Stark")
            .middleName("I")
            .build();

    List<Employee> employees = employeeService.getEmployeeByLastName("Stark");

    assertFalse(employees.isEmpty());
    assertEquals(1, employees.size());

    assertEmployee(expectedEmployee, Optional.ofNullable(employees.get(0)));
  }

  @Test
  void getEmployeeByMiddleNameShouldReturnNoEmployees() {
    List<Employee> employees = employeeService.getEmployeeByLastName("Spidey");

    assertTrue(employees.isEmpty());
  }

  @Test
  void getEmployeeByMiddleNameShouldReturnEmployee() {
    Map<Integer, Employee> expectedEmployeesMap = new HashMap<>();
    expectedEmployeesMap.put(
        12345,
        Employee.builder()
            .employeeNumber(12345)
            .firstName("Peter")
            .hiringDate("2023-06-06")
            .lastName("Parker")
            .middleName("S")
            .build());

    expectedEmployeesMap.put(
        12349,
        Employee.builder()
            .employeeNumber(12349)
            .firstName("Peter")
            .hiringDate("2023-06-09")
            .lastName("Quill")
            .middleName("S")
            .build());

    List<Employee> employees = employeeService.getEmployeeByMiddleName("S");

    assertFalse(employees.isEmpty());
    assertEquals(2, employees.size());

    employees.forEach(
        e ->
            assertEmployee(
                expectedEmployeesMap.get(e.getEmployeeNumber()), Optional.ofNullable(e)));
  }

  @Test
  void getEmployeeByHiringDateShouldReturnNoEmployees() {
    List<Employee> employees = employeeService.getEmployeeByHiringDate("2022-01-01");

    assertTrue(employees.isEmpty());
  }

  @Test
  void getEmployeeByHiringDateShouldReturnEmployee() {
    Map<Integer, Employee> expectedEmployeesMap = new HashMap<>();
    expectedEmployeesMap.put(
        12346,
        Employee.builder()
            .employeeNumber(12346)
            .firstName("Bruce")
            .hiringDate("2023-06-07")
            .lastName("Wayne")
            .middleName("B")
            .build());

    expectedEmployeesMap.put(
        12347,
        Employee.builder()
            .employeeNumber(12347)
            .firstName("Barry")
            .hiringDate("2023-06-07")
            .lastName("Allen")
            .middleName("F")
            .build());

    List<Employee> employees = employeeService.getEmployeeByHiringDate("2023-06-07");

    assertFalse(employees.isEmpty());
    assertEquals(2, employees.size());

    employees.forEach(
        e ->
            assertEmployee(
                expectedEmployeesMap.get(e.getEmployeeNumber()), Optional.ofNullable(e)));
  }

  @Test
  void getAllEmployeesShouldReturnEmployees() {
    Map<Integer, Employee> expectedEmployeesMap = new HashMap<>();
    expectedEmployeesMap.put(
        12345,
        Employee.builder()
            .employeeNumber(12345)
            .firstName("Peter")
            .hiringDate("2023-06-06")
            .lastName("Parker")
            .middleName("S")
            .build());

    expectedEmployeesMap.put(
        12346,
        Employee.builder()
            .employeeNumber(12346)
            .firstName("Bruce")
            .hiringDate("2023-06-07")
            .lastName("Wayne")
            .middleName("B")
            .build());

    expectedEmployeesMap.put(
        12347,
        Employee.builder()
            .employeeNumber(12347)
            .firstName("Barry")
            .hiringDate("2023-06-07")
            .lastName("Allen")
            .middleName("F")
            .build());

    expectedEmployeesMap.put(
        12348,
        Employee.builder()
            .employeeNumber(12348)
            .firstName("Tony")
            .hiringDate("2023-06-08")
            .lastName("Stark")
            .middleName("I")
            .build());

    expectedEmployeesMap.put(
        12349,
        Employee.builder()
            .employeeNumber(12349)
            .firstName("Peter")
            .hiringDate("2023-06-09")
            .lastName("Quill")
            .middleName("S")
            .build());

    expectedEmployeesMap.put(
        12350,
        Employee.builder()
            .employeeNumber(12350)
            .firstName("Arthur")
            .hiringDate("2023-06-10")
            .lastName("Curry")
            .middleName("A")
            .build());

    expectedEmployeesMap.put(
        12351,
        Employee.builder()
            .employeeNumber(12351)
            .firstName("Bruce")
            .hiringDate("2023-06-11")
            .lastName("Banner")
            .middleName("H")
            .build());

    List<Employee> employees = employeeService.getAll();

    assertFalse(employees.isEmpty());
    assertEquals(7, employees.size());

    employees.forEach(
        e ->
            assertEmployee(
                expectedEmployeesMap.get(e.getEmployeeNumber()), Optional.ofNullable(e)));
  }

  @Test
  void shouldDeleteEmployee() {
    List<Employee> employees = employeeService.getAll();
    assertEquals(7, employees.size());

    employeeService.deleteEmployeeByEmployeeNumber(12345);

    employees = employeeService.getAll();
    assertEquals(6, employees.size());

    Optional<Employee> optionalEmployee = employeeService.getEmployeeByEmployeeNumber(12345);

    assertFalse(optionalEmployee.isPresent());
  }

  private void assertEmployee(
      Employee expectedEmployee, Optional<Employee> actualOptionalEmployee) {
    assertTrue(actualOptionalEmployee.isPresent());

    Employee actualEmployee = actualOptionalEmployee.get();

    assertEquals(expectedEmployee.getEmployeeNumber(), actualEmployee.getEmployeeNumber());
    assertEquals(expectedEmployee.getFirstName(), actualEmployee.getFirstName());
    assertEquals(expectedEmployee.getHiringDate(), actualEmployee.getHiringDate());
    assertEquals(expectedEmployee.getLastName(), actualEmployee.getLastName());
    assertEquals(expectedEmployee.getMiddleName(), actualEmployee.getMiddleName());
  }

  private void addEmployees() {
    EmployeeRepository.setEmployees(new ArrayList<>());
    employeeService.addEmployee(
        Employee.builder()
            .employeeNumber(12345)
            .firstName("Peter")
            .hiringDate("2023-06-06")
            .lastName("Parker")
            .middleName("S")
            .build());

    employeeService.addEmployee(
        Employee.builder()
            .employeeNumber(12346)
            .firstName("Bruce")
            .hiringDate("2023-06-07")
            .lastName("Wayne")
            .middleName("B")
            .build());

    employeeService.addEmployee(
        Employee.builder()
            .employeeNumber(12347)
            .firstName("Barry")
            .hiringDate("2023-06-07")
            .lastName("Allen")
            .middleName("F")
            .build());

    employeeService.addEmployee(
        Employee.builder()
            .employeeNumber(12348)
            .firstName("Tony")
            .hiringDate("2023-06-08")
            .lastName("Stark")
            .middleName("I")
            .build());

    employeeService.addEmployee(
        Employee.builder()
            .employeeNumber(12349)
            .firstName("Peter")
            .hiringDate("2023-06-09")
            .lastName("Quill")
            .middleName("S")
            .build());

    employeeService.addEmployee(
        Employee.builder()
            .employeeNumber(12350)
            .firstName("Arthur")
            .hiringDate("2023-06-10")
            .lastName("Curry")
            .middleName("A")
            .build());

    employeeService.addEmployee(
        Employee.builder()
            .employeeNumber(12351)
            .firstName("Bruce")
            .hiringDate("2023-06-11")
            .lastName("Banner")
            .middleName("H")
            .build());
  }
}
