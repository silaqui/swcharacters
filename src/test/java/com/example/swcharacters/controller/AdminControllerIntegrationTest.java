package com.example.swcharacters.controller;

import com.example.swcharacters.data.StarWarsRepository;
import com.example.swcharacters.data.entity.PersonEntity;
import com.google.common.collect.Iterables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AdminControllerIntegrationTest {

    @Autowired
    private StarWarsRepository repository;

    @Autowired
    private AdminController tested;

    PersonEntity R2D2 = new PersonEntity(2, "R2D2", 0, 0);
    PersonEntity C3PO = new PersonEntity(3, "C3P0", 0, 0);

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
        repository.save(R2D2);
        repository.save(C3PO);
    }

    @Test
    public void shouldImportLuke() {
        //given
        Iterable<PersonEntity> before = repository.findAll();

        //when
        tested.importPerson(1);
        Iterable<PersonEntity> all = repository.findAll();

        //then
        assertThat(Iterables.size(before)).isEqualTo(2);
        assertThat(Iterables.size(all)).isEqualTo(3);

    }

}