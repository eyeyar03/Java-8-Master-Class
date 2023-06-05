package com.masterclass.employee.directory.service;

import com.masterclass.employee.directory.Employee;

@FunctionalInterface
public interface CommandAction {

    public void doAction(Employee employee);
}
