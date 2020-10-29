package com.example.swcharacters.service;

import com.example.swcharacters.data.SWApiDataSource;
import com.example.swcharacters.data.StarWarsRepository;
import com.example.swcharacters.data.entity.PersonEntity;
import com.example.swcharacters.data.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminService {

    private final StarWarsRepository repository;
    private final SWApiDataSource api;

    public Optional<Person> importPerson(int id) {
        Optional<Person> person = api.importPerson(id);
        person.ifPresent(value -> repository.save(PersonEntity.fromPerson(id, value)));
        return person;
    }

}
