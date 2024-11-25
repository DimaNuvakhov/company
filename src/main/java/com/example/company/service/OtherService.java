package com.example.company.service;

import com.example.company.model.Other;
import com.example.company.repository.OtherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OtherService {

    private final OtherRepository otherRepository;

    public List<Other> getAll() {
        return otherRepository.findAll();
    }

    public Other add(Other other) {
        return otherRepository.save(other);
    }

    public void delete(UUID id) {
        otherRepository.deleteById(id);
    }
}
