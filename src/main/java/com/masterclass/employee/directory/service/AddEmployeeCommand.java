package com.masterclass.employee.directory.service;

import com.masterclass.employee.directory.Employee;

public class AddEmployeeCommand implements CommandAction {

    @Override
    public void doAction(Employee employee) {
        EmployeeRepository.addEmployee(employee);
    }
}
