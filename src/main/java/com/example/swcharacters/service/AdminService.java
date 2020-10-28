package com.example.swcharacters.service;

import com.example.swcharacters.model.Person;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    public Optional<Person> importPerson(int id) {
        return Optional.empty();
    }

}
