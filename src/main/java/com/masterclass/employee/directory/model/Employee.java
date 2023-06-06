package com.masterclass.employee.directory.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Employee {

  private int employeeNumber;

  private String firstName;

  private String hiringDate;

  private String lastName;

  private String middleName;
}
