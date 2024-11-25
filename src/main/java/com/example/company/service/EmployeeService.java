package com.example.company.service;

import com.example.company.model.Employee;
import com.example.company.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private static final String SORT_BY_NAME = "name";
    private static final String SORT_BY_HIRE_DATE = "hireDate";

    private static final Map<String, Comparator<Employee>> SORTING_OPTIONS = Map.of(
            SORT_BY_NAME, Comparator.comparing(Employee::getName),
            SORT_BY_HIRE_DATE, Comparator.comparing(Employee::getHireDate)
    );

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAll(String sortBy) {
        List<Employee> employees = employeeRepository.findAll();
        Comparator<Employee> comparator = SORTING_OPTIONS.get(sortBy.toLowerCase());
        if (comparator != null) {
            employees.sort(comparator);
        }
        return employees;
    }

    public Employee add(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee update(UUID id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        existingEmployee.setName(employee.getName());
        existingEmployee.setBirthDate(employee.getBirthDate());
        existingEmployee.setHireDate(employee.getHireDate());
        return existingEmployee;
    }

    public void delete(UUID id) {
        employeeRepository.deleteById(id);
    }
}