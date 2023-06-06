package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.model.UserSelectionState;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.serviceimplementation.EmployeeServiceImpl;

import java.util.Optional;
import java.util.Scanner;

public class DeleteEmployeeAction implements CommandAction {

  private EmployeeService employeeService = new EmployeeServiceImpl();
  private final UserSelectionState userSelectionState;

  public DeleteEmployeeAction(UserSelectionState userSelectionState) {
    this.userSelectionState = userSelectionState;
  }

  @Override
  public void doAction() {
    System.out.println("You selected Delete Employee Record");
    System.out.print("Enter Employee Number: ");
    Scanner scan = new Scanner(System.in);
    int deleteEmp = scan.nextInt();
    Optional<Employee> employeeOptional = employeeService.getEmployeeByEmployeeNumber(deleteEmp);
    if (employeeOptional.isPresent()) {
      employeeService.deleteEmployeeByEmployeeNumber(deleteEmp);
      System.out.println("Employee " + employeeOptional.get().getFirstName() + "is deleted! ");
        userSelectionState.getPreviousCommandActions().pop().doAction();
      } else {
      //if nothing to delete should return to the input
      System.out.println("Nothing to delete! Employee not found!\n");
      userSelectionState.getPreviousCommandActions().pop().doAction();

    }
  }
}
