package com.example.swcharacters.service;

import com.example.swcharacters.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminService {

    public Optional<Person> importPerson(int id) {
        return Optional.empty();
    }

}
