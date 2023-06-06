package com.masterclass.employee.directory.menu.option;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Options {
  Option[] value() default {};
}
