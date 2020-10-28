package com.example.swcharacters.data;

import com.example.swcharacters.data.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StarWarsRepository extends CrudRepository<PersonEntity, Integer> {

    Optional<PersonEntity> findById(int id);

    List<PersonEntity> findAllByNameIgnoreCaseContaining(String name);

}
