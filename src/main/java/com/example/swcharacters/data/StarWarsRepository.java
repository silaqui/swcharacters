package com.example.swcharacters.data;

import com.example.swcharacters.data.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StarWarsRepository extends CrudRepository<PersonEntity, Integer> {

    List<PersonEntity> findAllByNameIgnoreCaseContaining(String name);

}
