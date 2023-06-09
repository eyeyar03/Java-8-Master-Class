package com.masterclass.employee.directory.util;

import com.masterclass.employee.directory.exceptions.FutureDateIsNotAllowedException;
import com.masterclass.employee.directory.menu.option.Option;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class InputHelper {

  public static int askUserToSelect(Class<?> clazz, String optionHeader, String instruction) {
    Option[] options = clazz.getAnnotationsByType(Option.class);

    Optional<Integer> optionalSelectedOption;
    do {
      optionalSelectedOption = getSelectedOption(options, optionHeader, instruction);
    } while (!optionalSelectedOption.isPresent());

    return optionalSelectedOption.get();
  }

  private static Optional<Integer> getSelectedOption(
      Option[] options, String optionHeader, String instruction) {
    System.out.println(optionHeader);

    Arrays.stream(options)
        .forEach(o -> System.out.println(String.format("[%d] %s", o.key(), o.label())));

    System.out.print("\n" + instruction);

    Scanner scanner = new Scanner(System.in);

    Optional<Integer> optionalSelectedOption = Optional.empty();

    try {
      int selectedOption = scanner.nextInt();

      if (!isSelectedOptionValid(options, selectedOption)) {
        throw new IllegalArgumentException("Selected option is not valid.");
      }

      optionalSelectedOption = Optional.of(selectedOption);
    } catch (Exception e) {
      System.out.println("Invalid entry. Try again.\n");
    }

    return optionalSelectedOption;
  }

  private static boolean isSelectedOptionValid(Option[] options, int selectedOption) {
    return Arrays.stream(options).anyMatch(o -> o.key() == selectedOption);
  }

  public static int askUserToProvideEmployeeNumber() {
    Optional<Integer> optionalEmployeeNumber;
    do {
      optionalEmployeeNumber = getEmployeeNumber();
    } while (!optionalEmployeeNumber.isPresent());

    return optionalEmployeeNumber.get();
  }

  private static Optional<Integer> getEmployeeNumber() {
    Optional<Integer> optionalEmployeeNumber = Optional.empty();

    Scanner scanner = new Scanner(System.in);
    try {
      System.out.print("Employee Number: ");
      int employeeNumber = scanner.nextInt();

      optionalEmployeeNumber = Optional.of(employeeNumber);

    } catch (Exception e) {
      System.out.println("Invalid entry. Try again.\n");
    }

    return optionalEmployeeNumber;
  }

  public static String askUserToProvideInput(String instruction) {
    Optional<String> optionalInput;

    do {
      optionalInput = getInput(instruction);
    } while (!optionalInput.isPresent());

    return optionalInput.get();
  }

  private static Optional<String> getInput(String instruction) {
    Optional<String> optionalProvidedInput = Optional.empty();

    System.out.print(instruction);

    Scanner scanner = new Scanner(System.in);
    String providedInput = scanner.nextLine();

    if ((Objects.nonNull(providedInput) && (providedInput.trim().length() > 0))) {
      optionalProvidedInput = Optional.of(providedInput);
    } else {
      System.out.println("Please provide a valid input.");
    }

    return optionalProvidedInput;
  }

  public static LocalDate askUserToProvideHiringDate(String instruction) {
    Optional<LocalDate> optionalHiringDate;
    do {
      optionalHiringDate = getHiringDate(instruction);
    } while (!optionalHiringDate.isPresent());

    return optionalHiringDate.get();
  }

  private static Optional<LocalDate> getHiringDate(String instruction) {
    Optional<LocalDate> optionalHiringDate = Optional.empty();

    Scanner scanner = new Scanner(System.in);
    try {
      System.out.print(instruction);
      String hiringDate = scanner.nextLine();

      LocalDate hiringDateFormatted =
          LocalDate.parse(hiringDate, DateTimeFormatterEnum.YYYYhMMhDD.getDateTimeFormatter());

      if (hiringDateFormatted.isAfter(LocalDate.now())) {
        throw new FutureDateIsNotAllowedException();
      }

      optionalHiringDate = Optional.of(hiringDateFormatted);

    } catch (FutureDateIsNotAllowedException e) {
      System.out.println("Invalid Date. Future Date is not Allowed");
    } catch (Exception e) {
      System.out.println("Invalid Date Format. Please enter date in yyyy-MM-dd format");
    }

    return optionalHiringDate;
  }
}
