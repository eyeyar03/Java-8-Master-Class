package com.masterclass.employee.directory.util;

import com.masterclass.employee.directory.model.Employee;
import java.util.Comparator;

public enum SortEnum {
  BY_EMPLOYEE_NUMBER(Comparator.comparingInt(Employee::getEmployeeNumber)),

  BY_FIRST_NAME(Comparator.comparing(Employee::getFirstName)),

  BY_LAST_NAME(Comparator.comparing(Employee::getLastName)),

  BY_HIRING_DATE(Comparator.comparing(Employee::getHiringDate));

  private Comparator<Employee> comparator;

  SortEnum(Comparator<Employee> comparator) {
    this.comparator = comparator;
  }

  public Comparator<Employee> getComparator() {
    return comparator;
  }

  public static SortEnum defaultSort() {
    return BY_EMPLOYEE_NUMBER;
  }
}
