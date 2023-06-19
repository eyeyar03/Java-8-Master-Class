package com.masterclass.employee.directory.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Employee {

  private int employeeNumber;

  private String firstName;

  private LocalDate hiringDate;

  private String lastName;

  private String middleName;
}
