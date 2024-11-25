package com.example.company.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "hire_date")
    private LocalDate hireDate;

    public Employee(String name, LocalDate birthDate, LocalDate hireDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
    }
}
