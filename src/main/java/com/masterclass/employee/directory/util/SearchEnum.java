package com.masterclass.employee.directory.util;

import com.masterclass.employee.directory.model.Employee;

import java.util.Comparator;

public enum SearchEnum {
  BY_EMPLOYEE_NUMBER(Comparator.comparingInt(Employee::getEmployeeNumber)),

  BY_FIRST_NAME(Comparator.comparing(Employee::getFirstName)),

  BY_MIDDLE_NAME(Comparator.comparing(Employee::getMiddleName)),

  BY_LAST_NAME(Comparator.comparing(Employee::getLastName)),

  BY_HIRING_DATE(Comparator.comparing(Employee::getHiringDate));

  private Comparator<Employee> comparator;

  SearchEnum(Comparator<Employee> comparator) {
    this.comparator = comparator;
  }

  public Comparator<Employee> getComparator() {
    return comparator;
  }

  public static SearchEnum defaultSearch() {
    return BY_EMPLOYEE_NUMBER;
  }
}
