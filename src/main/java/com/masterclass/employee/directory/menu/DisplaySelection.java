package com.masterclass.employee.directory.menu;

import java.util.Scanner;
import java.util.function.Consumer;

public class DisplaySelection {

    //+++++++++++++++++++++++++MAIN MENU+++++++++++++++++++++++++//
    public void showMainMenu() {
        System.out.println("Main Option");
        System.out.println("[1] List All Employee Records");
        System.out.println("[2] Add New Employee Record");
        System.out.println("[3] Delete Employee Record");
        System.out.println("[4] Search Employee Record");
        System.out.println("[-1] exit\n");
        System.out.print("Enter action type: ");

        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();

        Consumer<Integer> option = selectMainOption(choice);
        if (option != null) {
            option.accept(choice);
        } else {
            showMainMenu();
        }
    }

    public Consumer<Integer> selectMainOption(int choice) {
        switch (choice){
            case 1:
                return (selected) -> {
                    System.out.println("You selected List All Employee Records");
                  showSortActionMenu();
                };
            case 2:
                return (selected) -> {
                    System.out.println("You selected Add New Employee Record");
                };
            case 3:
                return (selected) -> {
                    System.out.println("You selected Delete Employee Record");
                };
            case 4:
                return (selected) -> {
                    System.out.println("You selected Search Employee Record");
                    showSearchActionMenu();
                };
            case -1:
                return (selected) -> {
                    System.out.println("You selected to exit the program");
                };
            default:
                return null;
        }
    }

    //+++++++++++++++++++++++++SORT ACTION MENU+++++++++++++++++++++++++//
    public void showSortActionMenu() {
        System.out.println("Choose an action");
        System.out.println("[1] Sorted by Employee Number");
        System.out.println("[2] Sorted by First Name");
        System.out.println("[3] Sorted by Last Name");
        System.out.println("[4] Sorted by Hiring Date");
        System.out.println("[-1] back\n");
        System.out.print("Select action: ");

        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();

        Consumer<Integer> option = selectSortActionMenu(choice);
        if (option != null) {
            option.accept(choice);
        } else {
           showSortActionMenu();
        }
    }
    public Consumer<Integer> selectSortActionMenu(int choice) {
        switch (choice){
            case 1:
                return (selected) -> {
                    System.out.println("You selected Sorted by Employee Number");
                };
            case 2:
                return (selected) -> {
                    System.out.println("You selected Sorted by First Name");
                };
            case 3:
                return (selected) -> {
                    System.out.println("You selected Sorted by Last Name");
                };
            case 4:
                return (selected) -> {
                    System.out.println("You selected Sorted by Hiring Date");
                };
            case -1:
                return (selected) -> {
                    System.out.println("You selected back to main menu");
                    showMainMenu();
                };
            default:
                return (selected) -> {
                    showSortActionMenu();
                };
        }
    }
    //+++++++++++++++++++++++++SEARCH ACTION MENU+++++++++++++++++++++++++//

    public void showSearchActionMenu() {
        System.out.println("Choose an action");
        System.out.println("[1] Search by Employee Number");
        System.out.println("[2] Search by First Name");
        System.out.println("[3] Search by Middle Name");
        System.out.println("[4] Search by Last Name");
        System.out.println("[5] Search by Hiring Date");
        System.out.println("[-1] back\n");
        System.out.print("Select action: ");

        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();

        Consumer<Integer> option = selectSearchActionMenu(choice);
        if (option != null) {
            option.accept(choice);
        } else {
            showSearchActionMenu();
        }
    }
    public Consumer<Integer> selectSearchActionMenu(int choice) {
        switch (choice){
            case 1:
                return (selected) -> {
                    System.out.println("You selected Search by Employee Number");
                };
            case 2:
                return (selected) -> {
                    System.out.println("You selected Search by First Name");
                };
            case 3:
                return (selected) -> {
                    System.out.println("You selected Search by Middle Name");
                };
            case 4:
                return (selected) -> {
                    System.out.println("You selected Search by Last Name");
                };
            case 5:
                return (selected) -> {
                    System.out.println("You selected Sorted by Hiring Date");
                };
            case -1:
                return (selected) -> {
                    System.out.println("You selected back to main menu");
                    showMainMenu();
                };
            default:
                return (selected) -> {
                    showSearchActionMenu();
                };
        }
    }
}
