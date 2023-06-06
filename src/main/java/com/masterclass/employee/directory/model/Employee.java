package com.masterclass.employee.directory.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class Employee {

  private int employeeNumber;

  private String firstName;

  private String hiringDate;

  private String lastName;

  private String middleName;

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    int empNumberWidth = 45;
    int nameWidth = 45;
    int dateHiredWidth = 40;

    builder.append(String.format("%-" + empNumberWidth + "s%-" + nameWidth + "s%-" + dateHiredWidth + "s", employeeNumber,firstName.concat(" ").concat(middleName).concat(" ").concat(lastName), hiringDate));

    String employeeFormat = builder.toString();

    return employeeFormat;
  }
}
