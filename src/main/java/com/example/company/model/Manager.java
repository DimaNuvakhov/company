package com.example.company.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class Manager extends Employee {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Employee> employees;

    public Manager(String name, LocalDate birthDate, LocalDate hireDate) {
        super(name, birthDate, hireDate);
    }
}

