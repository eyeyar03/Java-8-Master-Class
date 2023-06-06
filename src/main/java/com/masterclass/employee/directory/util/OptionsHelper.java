package com.masterclass.employee.directory.util;

import com.masterclass.employee.directory.menu.option.Option;
import java.util.Arrays;

public class OptionsHelper {

  public static void printOptions(Class<?> clazz, String optionHeader, String instruction) {
    Option[] options = clazz.getAnnotationsByType(Option.class);

    System.out.println(optionHeader);

    Arrays.stream(options)
        .forEach(o -> System.out.println(String.format("[%d] %s", o.key(), o.label())));

    System.out.println("\n" + instruction);
  }
}
