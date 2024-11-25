package com.example.company.controller;

import com.example.company.model.Manager;
import com.example.company.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping
    public List<Manager> getAll() {
        return managerService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('client_manager')")
    public ResponseEntity<Manager> add(@RequestBody Manager manager) {
        return ResponseEntity.ok(managerService.add(manager));
    }

    @PutMapping("/{id}/assign")
    @PreAuthorize("hasRole('client_manager')")
    public ResponseEntity<Void> assignEmployeeToManager(@PathVariable UUID id, @RequestParam UUID employeeId) {
        managerService.assignEmployeeToManager(id, employeeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('client_manager')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        managerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{managerId}/promote")
    @PreAuthorize("hasRole('client_manager')")
    public ResponseEntity<Void> promoteEmployee(
            @PathVariable UUID managerId,
            @RequestParam UUID employeeId,
            @RequestParam String targetType
    ) {
        managerService.promoteEmployee(employeeId, managerId, targetType);
        return ResponseEntity.ok().build();
    }
}
