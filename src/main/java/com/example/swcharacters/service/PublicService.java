package com.example.swcharacters.service;

import com.example.swcharacters.data.StarWarsRepository;
import com.example.swcharacters.data.entity.PersonEntity;
import com.example.swcharacters.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PublicService {

    private final StarWarsRepository repository;
    private final ValidatorService validator;

    public Optional<Person> getById(int id) {
        return repository.findById(id).map(PersonEntity::toPerson);
    }

    public List<Person> getByName(String name) {
        return repository.findAllByNameIgnoreCaseContaining(name).stream()
                .filter(validator::isValidResult)
                .map(PersonEntity::toPerson)
                .collect(Collectors.toList());
    }

}
