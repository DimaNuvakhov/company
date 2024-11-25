package com.example.company.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class Other extends Employee {

    private String description;

    public Other(String name, LocalDate birthDate, LocalDate hireDate, String description) {
        super(name, birthDate, hireDate);
        this.description = description;
    }
}

