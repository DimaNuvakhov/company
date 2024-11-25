package com.example.company.controller;

import com.example.company.model.Employee;
import com.example.company.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAll(@RequestParam(required = false) String sortBy) {
        return employeeService.getAll(sortBy);
    }

    @PostMapping
    @PreAuthorize("hasRole('client_manager')")
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.add(employee));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('client_manager')")
    public ResponseEntity<Employee> update(@PathVariable UUID id, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.update(id, employee));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('client_manager')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
