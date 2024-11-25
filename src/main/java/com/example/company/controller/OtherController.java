package com.example.company.controller;

import com.example.company.model.Other;
import com.example.company.service.OtherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/others")
@RequiredArgsConstructor
public class OtherController {

    private final OtherService otherService;

    @GetMapping
    public List<Other> getAll() {
        return otherService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('client_manager')")
    public ResponseEntity<Other> add(@RequestBody Other other) {
        return ResponseEntity.ok(otherService.add(other));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('client_manager')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        otherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}