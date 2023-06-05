package com.masterclass.employee.directory;

import com.masterclass.employee.directory.service.AddEmployeeCommand;
import com.masterclass.employee.directory.service.CommandAction;
import com.masterclass.employee.directory.service.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddEmployeeTest {

    private CommandAction addEmployee;

    @BeforeEach
    void setup() {
        addEmployee = new AddEmployeeCommand();
    }

    @Test
    void shouldAddEmployee() {
        Employee employee = Employee.builder()
                .employeeNumber(1234)
                .firstName("Aaron")
                .lastName("Macandili")
                .middleName("P")
                .hiringDate("2023-05-06")
                .build();

        assertTrue(EmployeeRepository.getEmployees().isEmpty());

        addEmployee.doAction(employee);

        assertNotNull(EmployeeRepository.getEmployees().get(0));
    }
}
