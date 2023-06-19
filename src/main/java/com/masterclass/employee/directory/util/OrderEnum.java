package com.masterclass.employee.directory.util;

public enum OrderEnum {
  ASC,
  DESC;

  public static OrderEnum defaultOrder() {
    return ASC;
  }
}
