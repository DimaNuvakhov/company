package com.example.company.model;

public enum EmployeeType {
    MANAGER("manager"),
    OTHER("other"),
    EMPLOYEE("employee");

    private final String value;

    EmployeeType(String value) {
        this.value = value;
    }
}
