package com.example.company.service;

import com.example.company.exception.EmployeeNotFoundException;
import com.example.company.exception.ManagerNotFoundException;
import com.example.company.model.Employee;
import com.example.company.model.EmployeeType;
import com.example.company.model.Manager;
import com.example.company.model.Other;
import com.example.company.repository.EmployeeRepository;
import com.example.company.repository.ManagerRepository;
import com.example.company.repository.OtherRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.TargetType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.example.company.model.EmployeeType.MANAGER;
import static com.example.company.model.EmployeeType.OTHER;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final EmployeeRepository employeeRepository;
    private final OtherRepository otherRepository;

    public List<Manager> getAll() {
        return managerRepository.findAll();
    }

    public Manager add(Manager manager) {
        return managerRepository.save(manager);
    }

    @Transactional
    public void assignEmployeeToManager(UUID managerId, UUID employeeId) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(ManagerNotFoundException::new);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(ManagerNotFoundException::new);
        manager.getEmployees().add(employee);
    }

    public void delete(UUID id) {
        managerRepository.deleteById(id);
    }

    @Transactional
    public void promoteEmployee(UUID employeeId, UUID sourceManagerId, String type) {
        Manager sourceManager = managerRepository.findById(sourceManagerId)
                .orElseThrow(ManagerNotFoundException::new);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(EmployeeNotFoundException::new);

        if (!sourceManager.getEmployees().remove(employee)) {
            throw new IllegalArgumentException("Employee does not belong to the specified manager");
        }

        EmployeeType employeeType = EmployeeType.valueOf(type);

        switch (employeeType) {
            case MANAGER -> promoteToManager(employee);
            case OTHER -> promoteToOther(employee);
        }
    }

    private void promoteToManager(Employee employee) {
        Manager newManager = new Manager();
        newManager.setName(employee.getName());
        newManager.setBirthDate(employee.getBirthDate());
        managerRepository.save(newManager);
    }

    private void promoteToOther(Employee employee) {
        Other other = new Other();
        other.setName(employee.getName());
        other.setDescription("Good Employee");
        otherRepository.save(other);
    }
}
