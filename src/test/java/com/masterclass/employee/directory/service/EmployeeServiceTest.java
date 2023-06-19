package com.masterclass.employee.directory.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.repository.EmployeeRepository;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import com.masterclass.employee.directory.util.SortEnum;
import java.time.LocalDate;
import java.time.Month;
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
            .hiringDate(LocalDate.of(2023, Month.JUNE, 7))
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
            .hiringDate(LocalDate.of(2023, Month.JUNE, 6))
            .lastName("Parker")
            .middleName("S")
            .build();

    Optional<Employee> optionalEmployee = employeeService.getEmployeeByEmployeeNumber(12345);

    assertEmployee(expectedEmployee, optionalEmployee);
  }

  @Test
  void getEmployeeByFirstNameShouldReturnNoEmployees() {
    List<Employee> employees =
        employeeService.getEmployeeByFirstName("Spidey", SortEnum.defaultSort());

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
            .hiringDate(LocalDate.of(2023, Month.JUNE, 7))
            .lastName("Wayne")
            .middleName("B")
            .build());

    expectedEmployeesMap.put(
        12351,
        Employee.builder()
            .employeeNumber(12351)
            .firstName("Bruce")
            .hiringDate(LocalDate.of(2023, Month.JUNE, 11))
            .lastName("Banner")
            .middleName("H")
            .build());

    List<Employee> employees =
        employeeService.getEmployeeByFirstName("Bruce", SortEnum.defaultSort());

    assertFalse(employees.isEmpty());
    assertEquals(2, employees.size());

    employees.forEach(
        e ->
            assertEmployee(
                expectedEmployeesMap.get(e.getEmployeeNumber()), Optional.ofNullable(e)));
  }

  @Test
  void getEmployeeByFirstNameShouldReturnEmployees_sortedByEmployeeNumber() {
    List<Employee> employees =
        employeeService.getEmployeeByFirstName("Bruce", SortEnum.BY_EMPLOYEE_NUMBER);

    assertEquals(2, employees.size());

    assertEquals(12346, employees.get(0).getEmployeeNumber());
    assertEquals(12351, employees.get(1).getEmployeeNumber());
  }

  @Test
  void getEmployeeByFirstNameShouldReturnEmployees_sortedByFirstName() {
    List<Employee> employees =
        employeeService.getEmployeeByFirstName("Bruce", SortEnum.BY_FIRST_NAME);

    assertEquals(2, employees.size());

    assertEquals("Bruce", employees.get(0).getFirstName());
    assertEquals("Bruce", employees.get(1).getFirstName());
  }

  @Test
  void getEmployeeByFirstNameShouldReturnEmployees_sortedByLastName() {
    List<Employee> employees =
        employeeService.getEmployeeByFirstName("Bruce", SortEnum.BY_LAST_NAME);

    assertEquals(2, employees.size());

    assertEquals("Banner", employees.get(0).getLastName());
    assertEquals("Wayne", employees.get(1).getLastName());
  }

  @Test
  void getEmployeeByFirstNameShouldReturnEmployees_sortedByHiringDate() {
    List<Employee> employees =
        employeeService.getEmployeeByFirstName("Bruce", SortEnum.BY_HIRING_DATE);

    assertEquals(2, employees.size());

    assertEquals(LocalDate.of(2023, Month.JUNE, 7), employees.get(0).getHiringDate());
    assertEquals(LocalDate.of(2023, Month.JUNE, 11), employees.get(1).getHiringDate());
  }

  @Test
  void getEmployeeByLastNameShouldReturnNoEmployees() {
    List<Employee> employees =
        employeeService.getEmployeeByLastName("Spidey", SortEnum.defaultSort());

    assertTrue(employees.isEmpty());
  }

  @Test
  void getEmployeeByLastNameShouldReturnEmployees() {
    Map<Integer, Employee> expectedEmployeesMap = new HashMap<>();
    expectedEmployeesMap.put(
        12350,
        Employee.builder()
            .employeeNumber(12350)
            .firstName("Arthur")
            .hiringDate(LocalDate.of(2023, Month.JUNE, 10))
            .lastName("Curry")
            .middleName("A")
            .build());

    expectedEmployeesMap.put(
        12352,
        Employee.builder()
            .employeeNumber(12352)
            .firstName("Aaron")
            .hiringDate(LocalDate.of(2023, Month.MAY, 10))
            .lastName("Curry")
            .middleName("A")
            .build());

    List<Employee> employees =
        employeeService.getEmployeeByLastName("Curry", SortEnum.defaultSort());

    assertFalse(employees.isEmpty());
    assertEquals(2, employees.size());

    employees.forEach(
        e ->
            assertEmployee(
                expectedEmployeesMap.get(e.getEmployeeNumber()), Optional.ofNullable(e)));
  }

  @Test
  void getEmployeeByLastNameShouldReturnEmployees_sortedByEmployeeNumber() {
    List<Employee> employees =
        employeeService.getEmployeeByLastName("Curry", SortEnum.BY_EMPLOYEE_NUMBER);

    assertEquals(2, employees.size());

    assertEquals(12350, employees.get(0).getEmployeeNumber());
    assertEquals(12352, employees.get(1).getEmployeeNumber());
  }

  @Test
  void getEmployeeByLastNameShouldReturnEmployees_sortedByFirstName() {
    List<Employee> employees =
        employeeService.getEmployeeByLastName("Curry", SortEnum.BY_FIRST_NAME);

    assertEquals(2, employees.size());

    assertEquals("Aaron", employees.get(0).getFirstName());
    assertEquals("Arthur", employees.get(1).getFirstName());
  }

  @Test
  void getEmployeeByLastNameShouldReturnEmployees_sortedByLastName() {
    List<Employee> employees =
        employeeService.getEmployeeByLastName("Curry", SortEnum.BY_LAST_NAME);

    assertEquals(2, employees.size());

    assertEquals("Curry", employees.get(0).getLastName());
    assertEquals("Curry", employees.get(1).getLastName());
  }

  @Test
  void getEmployeeByLastNameShouldReturnEmployees_sortedByHiringDate() {
    List<Employee> employees =
        employeeService.getEmployeeByLastName("Curry", SortEnum.BY_HIRING_DATE);

    assertEquals(2, employees.size());

    assertEquals(LocalDate.of(2023, Month.MAY, 10), employees.get(0).getHiringDate());
    assertEquals(LocalDate.of(2023, Month.JUNE, 10), employees.get(1).getHiringDate());
  }

  @Test
  void getEmployeeByMiddleNameShouldReturnNoEmployees() {
    List<Employee> employees =
        employeeService.getEmployeeByLastName("Spidey", SortEnum.defaultSort());

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
            .hiringDate(LocalDate.of(2023, Month.JUNE, 6))
            .lastName("Parker")
            .middleName("S")
            .build());

    expectedEmployeesMap.put(
        12349,
        Employee.builder()
            .employeeNumber(12349)
            .firstName("Peter")
            .hiringDate(LocalDate.of(2023, Month.JUNE, 1))
            .lastName("Quill")
            .middleName("S")
            .build());

    List<Employee> employees = employeeService.getEmployeeByMiddleName("S", SortEnum.defaultSort());

    assertFalse(employees.isEmpty());
    assertEquals(2, employees.size());

    employees.forEach(
        e ->
            assertEmployee(
                expectedEmployeesMap.get(e.getEmployeeNumber()), Optional.ofNullable(e)));
  }

  @Test
  void getEmployeeByMiddleNameShouldReturnEmployees_sortedByEmployeeNumber() {
    List<Employee> employees =
        employeeService.getEmployeeByMiddleName("S", SortEnum.BY_EMPLOYEE_NUMBER);

    assertEquals(2, employees.size());

    assertEquals(12345, employees.get(0).getEmployeeNumber());
    assertEquals(12349, employees.get(1).getEmployeeNumber());
  }

  @Test
  void getEmployeeByMiddleNameShouldReturnEmployees_sortedByFirstName() {
    List<Employee> employees = employeeService.getEmployeeByMiddleName("S", SortEnum.BY_FIRST_NAME);

    assertEquals(2, employees.size());

    assertEquals("Peter", employees.get(0).getFirstName());
    assertEquals("Peter", employees.get(1).getFirstName());
  }

  @Test
  void getEmployeeByMiddleNameShouldReturnEmployees_sortedByLastName() {
    List<Employee> employees = employeeService.getEmployeeByMiddleName("S", SortEnum.BY_LAST_NAME);

    assertEquals(2, employees.size());

    assertEquals("Parker", employees.get(0).getLastName());
    assertEquals("Quill", employees.get(1).getLastName());
  }

  @Test
  void getEmployeeByMiddleNameShouldReturnEmployees_sortedByHiringDate() {
    List<Employee> employees =
        employeeService.getEmployeeByMiddleName("S", SortEnum.BY_HIRING_DATE);

    assertEquals(2, employees.size());

    assertEquals(LocalDate.of(2023, Month.JUNE, 1), employees.get(0).getHiringDate());
    assertEquals(LocalDate.of(2023, Month.JUNE, 6), employees.get(1).getHiringDate());
  }

  @Test
  void getEmployeeByHiringDateShouldReturnNoEmployees() {
    List<Employee> employees =
        employeeService.getEmployeeByHiringDate(
            LocalDate.of(2022, Month.JANUARY, 1), SortEnum.defaultSort());

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
            .hiringDate(LocalDate.of(2023, Month.JUNE, 7))
            .lastName("Wayne")
            .middleName("B")
            .build());

    expectedEmployeesMap.put(
        12347,
        Employee.builder()
            .employeeNumber(12347)
            .firstName("Barry")
            .hiringDate(LocalDate.of(2023, Month.JUNE, 7))
            .lastName("Allen")
            .middleName("F")
            .build());

    List<Employee> employees =
        employeeService.getEmployeeByHiringDate(
            LocalDate.of(2023, Month.JUNE, 7), SortEnum.defaultSort());

    assertFalse(employees.isEmpty());
    assertEquals(2, employees.size());

    employees.forEach(
        e ->
            assertEmployee(
                expectedEmployeesMap.get(e.getEmployeeNumber()), Optional.ofNullable(e)));
  }

  @Test
  void getEmployeeByHiringDateShouldReturnEmployees_sortedByEmployeeNumber() {
    List<Employee> employees =
        employeeService.getEmployeeByHiringDate(
            LocalDate.of(2023, Month.JUNE, 7), SortEnum.BY_EMPLOYEE_NUMBER);

    assertEquals(2, employees.size());

    assertEquals(12346, employees.get(0).getEmployeeNumber());
    assertEquals(12347, employees.get(1).getEmployeeNumber());
  }

  @Test
  void getEmployeeByHiringDateShouldReturnEmployees_sortedByFirstName() {
    List<Employee> employees =
        employeeService.getEmployeeByHiringDate(
            LocalDate.of(2023, Month.JUNE, 7), SortEnum.BY_FIRST_NAME);

    assertEquals(2, employees.size());

    assertEquals("Barry", employees.get(0).getFirstName());
    assertEquals("Bruce", employees.get(1).getFirstName());
  }

  @Test
  void getEmployeeByHiringDateShouldReturnEmployees_sortedByLastName() {
    List<Employee> employees =
        employeeService.getEmployeeByHiringDate(
            LocalDate.of(2023, Month.JUNE, 7), SortEnum.BY_LAST_NAME);

    assertEquals(2, employees.size());

    assertEquals("Allen", employees.get(0).getLastName());
    assertEquals("Wayne", employees.get(1).getLastName());
  }

  @Test
  void getEmployeeByHiringDateShouldReturnEmployees_sortedByHiringDate() {
    List<Employee> employees =
        employeeService.getEmployeeByHiringDate(
            LocalDate.of(2023, Month.JUNE, 7), SortEnum.BY_HIRING_DATE);

    assertEquals(2, employees.size());

    assertEquals(LocalDate.of(2023, Month.JUNE, 7), employees.get(0).getHiringDate());
    assertEquals(LocalDate.of(2023, Month.JUNE, 7), employees.get(1).getHiringDate());
  }

  @Test
  void getAllEmployeesShouldReturnEmployees() {
    Map<Integer, Employee> expectedEmployeesMap = new HashMap<>();
    expectedEmployeesMap.put(
        12345,
        Employee.builder()
            .employeeNumber(12345)
            .firstName("Peter")
            .hiringDate(LocalDate.of(2023, Month.JUNE, 6))
            .lastName("Parker")
            .middleName("S")
            .build());

    expectedEmployeesMap.put(
        12346,
        Employee.builder()
            .employeeNumber(12346)
            .firstName("Bruce")
            .hiringDate(LocalDate.of(2023, Month.JUNE, 7))
            .lastName("Wayne")
            .middleName("B")
            .build());

    expectedEmployeesMap.put(
        12347,
        Employee.builder()
            .employeeNumber(12347)
            .firstName("Barry")
            .hiringDate(LocalDate.of(2023, Month.JUNE, 7))
            .lastName("Allen")
            .middleName("F")
            .build());

    expectedEmployeesMap.put(
        12348,
        Employee.builder()
            .employeeNumber(12348)
            .firstName("Tony")
            .hiringDate(LocalDate.of(2023, Month.JUNE, 8))
            .lastName("Stark")
            .middleName("I")
            .build());

    expectedEmployeesMap.put(
        12349,
        Employee.builder()
            .employeeNumber(12349)
            .firstName("Peter")
            .hiringDate(LocalDate.of(2023, Month.JUNE, 1))
            .lastName("Quill")
            .middleName("S")
            .build());

    expectedEmployeesMap.put(
        12350,
        Employee.builder()
            .employeeNumber(12350)
            .firstName("Arthur")
            .hiringDate(LocalDate.of(2023, Month.JUNE, 10))
            .lastName("Curry")
            .middleName("A")
            .build());

    expectedEmployeesMap.put(
        12351,
        Employee.builder()
            .employeeNumber(12351)
            .firstName("Bruce")
            .hiringDate(LocalDate.of(2023, Month.JUNE, 11))
            .lastName("Banner")
            .middleName("H")
            .build());

    expectedEmployeesMap.put(
        12352,
        Employee.builder()
            .employeeNumber(12352)
            .firstName("Aaron")
            .hiringDate(LocalDate.of(2023, Month.MAY, 10))
            .lastName("Curry")
            .middleName("A")
            .build());

    List<Employee> employees = employeeService.getAll(SortEnum.defaultSort().getComparator());

    assertFalse(employees.isEmpty());
    assertEquals(8, employees.size());

    employees.forEach(
        e ->
            assertEmployee(
                expectedEmployeesMap.get(e.getEmployeeNumber()), Optional.ofNullable(e)));
  }

  @Test
  void getAllShouldReturnEmployees_sortedByEmployeeNumber() {
    List<Employee> employees = employeeService.getAll(SortEnum.BY_EMPLOYEE_NUMBER.getComparator());

    assertEquals(8, employees.size());

    assertEquals(12345, employees.get(0).getEmployeeNumber());
    assertEquals(12346, employees.get(1).getEmployeeNumber());
    assertEquals(12347, employees.get(2).getEmployeeNumber());
    assertEquals(12348, employees.get(3).getEmployeeNumber());
    assertEquals(12349, employees.get(4).getEmployeeNumber());
    assertEquals(12350, employees.get(5).getEmployeeNumber());
    assertEquals(12351, employees.get(6).getEmployeeNumber());
    assertEquals(12352, employees.get(7).getEmployeeNumber());
  }

  @Test
  void getAllShouldReturnEmployees_sortedByFirstName() {
    List<Employee> employees = employeeService.getAll(SortEnum.BY_FIRST_NAME.getComparator());

    assertEquals(8, employees.size());

    assertEquals("Aaron", employees.get(0).getFirstName());
    assertEquals("Arthur", employees.get(1).getFirstName());
    assertEquals("Barry", employees.get(2).getFirstName());
    assertEquals("Bruce", employees.get(3).getFirstName());
    assertEquals("Bruce", employees.get(4).getFirstName());
    assertEquals("Peter", employees.get(5).getFirstName());
    assertEquals("Peter", employees.get(6).getFirstName());
    assertEquals("Tony", employees.get(7).getFirstName());
  }

  @Test
  void getAllShouldReturnEmployees_sortedByLastName() {
    List<Employee> employees = employeeService.getAll(SortEnum.BY_LAST_NAME.getComparator());

    assertEquals(8, employees.size());

    assertEquals("Allen", employees.get(0).getLastName());
    assertEquals("Banner", employees.get(1).getLastName());
    assertEquals("Curry", employees.get(2).getLastName());
    assertEquals("Curry", employees.get(3).getLastName());
    assertEquals("Parker", employees.get(4).getLastName());
    assertEquals("Quill", employees.get(5).getLastName());
    assertEquals("Stark", employees.get(6).getLastName());
    assertEquals("Wayne", employees.get(7).getLastName());
  }

  @Test
  void getAllShouldReturnEmployees_sortedByHiringDate() {
    List<Employee> employees = employeeService.getAll(SortEnum.BY_HIRING_DATE.getComparator());

    assertEquals(8, employees.size());

    assertEquals(LocalDate.of(2023, Month.MAY, 10), employees.get(0).getHiringDate());
    assertEquals(LocalDate.of(2023, Month.JUNE, 1), employees.get(1).getHiringDate());
    assertEquals(LocalDate.of(2023, Month.JUNE, 6), employees.get(2).getHiringDate());
    assertEquals(LocalDate.of(2023, Month.JUNE, 7), employees.get(3).getHiringDate());
    assertEquals(LocalDate.of(2023, Month.JUNE, 7), employees.get(4).getHiringDate());
    assertEquals(LocalDate.of(2023, Month.JUNE, 8), employees.get(5).getHiringDate());
    assertEquals(LocalDate.of(2023, Month.JUNE, 10), employees.get(6).getHiringDate());
    assertEquals(LocalDate.of(2023, Month.JUNE, 11), employees.get(7).getHiringDate());
  }

  @Test
  void shouldDeleteEmployee() {
    List<Employee> employees = employeeService.getAll(SortEnum.defaultSort().getComparator());
    assertEquals(8, employees.size());

    employeeService.deleteEmployeeByEmployeeNumber(12345);

    employees = employeeService.getAll(SortEnum.defaultSort().getComparator());
    assertEquals(7, employees.size());

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
            .hiringDate(LocalDate.of(2023, Month.JUNE, 6))
            .lastName("Parker")
            .middleName("S")
            .build());

    employeeService.addEmployee(
        Employee.builder()
            .employeeNumber(12346)
            .firstName("Bruce")
            .hiringDate(LocalDate.of(2023, Month.JUNE, 7))
            .lastName("Wayne")
            .middleName("B")
            .build());

    employeeService.addEmployee(
        Employee.builder()
            .employeeNumber(12347)
            .firstName("Barry")
            .hiringDate(LocalDate.of(2023, Month.JUNE, 7))
            .lastName("Allen")
            .middleName("F")
            .build());

    employeeService.addEmployee(
        Employee.builder()
            .employeeNumber(12348)
            .firstName("Tony")
            .hiringDate(LocalDate.of(2023, Month.JUNE, 8))
            .lastName("Stark")
            .middleName("I")
            .build());

    employeeService.addEmployee(
        Employee.builder()
            .employeeNumber(12349)
            .firstName("Peter")
            .hiringDate(LocalDate.of(2023, Month.JUNE, 1))
            .lastName("Quill")
            .middleName("S")
            .build());

    employeeService.addEmployee(
        Employee.builder()
            .employeeNumber(12350)
            .firstName("Arthur")
            .hiringDate(LocalDate.of(2023, Month.JUNE, 10))
            .lastName("Curry")
            .middleName("A")
            .build());

    employeeService.addEmployee(
        Employee.builder()
            .employeeNumber(12351)
            .firstName("Bruce")
            .hiringDate(LocalDate.of(2023, Month.JUNE, 11))
            .lastName("Banner")
            .middleName("H")
            .build());

    employeeService.addEmployee(
        Employee.builder()
            .employeeNumber(12352)
            .firstName("Aaron")
            .hiringDate(LocalDate.of(2023, Month.MAY, 10))
            .lastName("Curry")
            .middleName("A")
            .build());
  }
}
