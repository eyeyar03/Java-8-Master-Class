package com.masterclass.employee.directory.menu.option;

import java.lang.annotation.Repeatable;

@Repeatable(value = Options.class)
public @interface Option {
  String label();

  int key();
}
