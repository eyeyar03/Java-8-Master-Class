package com.masterclass.employee.directory.menu;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.util.InputHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.masterclass.employee.directory.util.Constants.*;

public class ReadFromFileAction implements CommandAction{
    EmployeeService
    @Override
    public void doAction() {
        Optional<List<Employee>> optionalEmployees;

        do{
            optionalEmployees = parseEmployeeFromFile();
        }while(!optionalEmployees.isPresent());

        employeeService.addEmployee(employee);
    }

    private Optional<List<Employee>> parseEmployeeFromFile() {
        Optional<List<Employee>> optionalEmployees = Optional.empty();
        String fileName = InputHelper.askUserToProvideInput(INSTRUCTION_ENTER_FILENAME);

        try(Stream<String> lines = Files.lines(Paths.get(fileName))){
            List<Employee> employees = lines.skip(1).map(this::buildEmployee).collect(Collectors.toList());
            optionalEmployees = Optional.of(employees);

        }catch(Exception e){
            System.out.println("Error encountered reading file " +fileName);
        }

        return optionalEmployees;
    }

    private Employee buildEmployee(String line) {
        String[] employeeDetails = line.split(",");

        return Employee.builder().employeeNumber(Integer.parseInt(employeeDetails[0]))
                .firstName(employeeDetails[1])
                .middleName(employeeDetails[2])
                .lastName(employeeDetails[3])
                .hiringDate(getHiringDate(employeeDetails[4]))
                .build();
    }

    private LocalDate getHiringDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

        return LocalDate.parse(date,formatter);
    }

}
