package com.masterclass.employee.directory.serviceimplementation;

import com.masterclass.employee.directory.model.Employee;
import com.masterclass.employee.directory.repository.EmployeeRepository;
import com.masterclass.employee.directory.service.EmployeeService;
import com.masterclass.employee.directory.util.SortEnum;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public int addEmployee(Employee employee) {
        return EmployeeRepository.addEmployee(employee);
    }

    @Override
    public Optional<Employee> getEmployeeByEmployeeNumber(int employeeNumber) {
        return EmployeeRepository.getEmployees().stream()
                .filter(e -> e.getEmployeeNumber() == employeeNumber)
                .findFirst();
    }

    @Override
    public List<Employee> getEmployeeByFirstName(String firstName, SortEnum sortEnum) {
        return getMatchedEmployees(e -> e.getFirstName().toLowerCase().equalsIgnoreCase(firstName) || e.getFirstName().toLowerCase().contains(firstName), sortEnum);
    }

    @Override
    public List<Employee> getEmployeeByLastName(String lastName, SortEnum sortEnum) {
        return getMatchedEmployees(e -> e.getLastName().toLowerCase().equalsIgnoreCase(lastName) || e.getLastName().toLowerCase().contains(lastName), sortEnum);
    }

    @Override
    public List<Employee> getEmployeeByMiddleName(String middleName, SortEnum sortEnum) {
        return getMatchedEmployees(e -> e.getMiddleName().toLowerCase().equalsIgnoreCase(middleName) || e.getMiddleName().toLowerCase().contains(middleName), sortEnum);
    }

    @Override
    public List<Employee> getEmployeeByHiringDate(LocalDate hiringDate, SortEnum sortEnum) {
        return getMatchedEmployees(e -> e.getHiringDate().equals(hiringDate), sortEnum);
    }

    @Override
    public List<Employee> getAll(Comparator<Employee> employeeComparator) {
        return EmployeeRepository.getEmployees().stream()
                .sorted(employeeComparator)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> deleteEmployeeByEmployeeNumber(int employeeNumber) {
        EmployeeRepository.setEmployees(
                getMatchedEmployees(e -> e.getEmployeeNumber() != employeeNumber, SortEnum.defaultSort()));
        return Optional.empty();
    }

    @Override
    public void addAllEmployee(List<Employee> employees) {
        EmployeeRepository.addAllEmployees(employees);
    }

    private List<Employee> getMatchedEmployees(
            Predicate<Employee> employeePredicate, SortEnum sortEnum) {

        return EmployeeRepository.getEmployees().stream()
                .filter(employeePredicate)
                .sorted(sortEnum.getComparator())
                .collect(Collectors.toList());
    }
}
