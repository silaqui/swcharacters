package com.example.swcharacters.controller;

import com.example.swcharacters.data.StarWarsRepository;
import com.example.swcharacters.data.entity.PersonEntity;
import com.example.swcharacters.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PublicControllerIntegrationTest {

    @Autowired
    private StarWarsRepository repository;

    @Autowired
    private PublicController tested;

    PersonEntity R2D2 = new PersonEntity(2, "R2D2", 0, 0);
    PersonEntity C3PO = new PersonEntity(3, "C3P0", 0, 0);

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
        repository.save(R2D2);
        repository.save(C3PO);
    }

    @Test
    public void shouldNotFindLuke() {
        //given

        //when
        ResponseEntity<Person> actual = tested.getCharacter(1);

        //then
        assertThat(actual.getBody()).isNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldFindC3PO() {
        //given

        //when
        ResponseEntity<Person> actual = tested.getCharacter(3);

        //then
        assertThat(actual.getBody()).isNotNull();
        assertThat(actual.getBody()).isEqualTo(C3PO.toPerson());
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldSearchForR2D2() {
        //given

        //when
        ResponseEntity<List<Person>> actual = tested.search("d2");

        //then
        assertThat(actual.getBody()).isNotNull();
        assertThat(actual.getBody().size()).isEqualTo(1);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldSearchForNothing() {
        //given

        //when
        ResponseEntity<List<Person>> actual = tested.search("NoChanceAtAll");

        //then
        assertThat(actual.getBody()).isNotNull();
        assertThat(actual.getBody()).isEmpty();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}