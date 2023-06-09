package com.masterclass.employee.directory.util;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;
import java.time.LocalDate;

public final class TestDataUtil {

  private TestDataUtil() {}

  public static void generateTestData() {
    EmployeeService employeeService = new EmployeeServiceImpl();

    Employee emp1 =
        Employee.builder()
            .employeeNumber(20220607)
            .firstName("Aaron")
            .hiringDate(LocalDate.of(2022, 06, 07))
            .lastName("Macandili")
            .middleName("B")
            .build();

    Employee emp2 =
        Employee.builder()
            .employeeNumber(20230105)
            .firstName("Jerome")
            .hiringDate(LocalDate.of(2023, 01, 05))
            .lastName("Garcia")
            .middleName("G")
            .build();

    Employee emp3 =
        Employee.builder()
            .employeeNumber(20231005)
            .firstName("Alejandro Jr")
            .hiringDate(LocalDate.of(2022, 10, 05))
            .lastName("Alinsangan")
            .middleName("Z")
            .build();

    employeeService.addEmployee(emp1);
    employeeService.addEmployee(emp2);
    employeeService.addEmployee(emp3);
  }
}
