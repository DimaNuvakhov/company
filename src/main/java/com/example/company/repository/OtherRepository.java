package com.example.company.repository;

import com.example.company.model.Other;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface OtherRepository extends JpaRepository<Other, UUID> {
}
