package com.example.swcharacters.service;

import com.example.swcharacters.data.StarWarsRepository;
import com.example.swcharacters.data.entity.PersonEntity;
import com.example.swcharacters.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PublicServiceTest {

    @Mock
    private StarWarsRepository repository;
    @Mock
    private ValidatorService validator;

    @InjectMocks
    public PublicService tested;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldGetById() {
        //given
        int id = 42;
        PersonEntity entity = new PersonEntity(id, "", 0, 0);
        Optional<PersonEntity> result = Optional.of(entity);
        when(repository.findById(id)).thenReturn(result);
        Person expected = entity.toPerson();

        //when
        Optional<Person> actual = tested.getById(id);

        //then
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get()).isEqualTo(expected);
    }

    @Test
    void shouldGetById_NotFound() {
        //given
        int id = 42;
        when(repository.findById(id)).thenReturn(Optional.empty());

        //when
        Optional<Person> actual = tested.getById(id);

        //then
        assertThat(actual.isPresent()).isFalse();
    }

    @Test
    void getByName_emptyResult() {
        //given
        String name = "Lucy";
        when(repository.findAllByNameIgnoreCaseContaining(name)).thenReturn(new ArrayList<>());

        //when
        List<Person> actual = tested.getByName(name);

        //then
        assertThat(actual).isNotNull();
        assertThat(actual.isEmpty()).isTrue();
    }

    @Test
    void getByName_filteredResult() {
        //given
        String name = "Lucy";
        List<PersonEntity> result = Collections.singletonList(new PersonEntity());
        when(repository.findAllByNameIgnoreCaseContaining(name)).thenReturn(result);
        when(validator.isValidResult(any())).thenReturn(false);

        //when
        List<Person> actual = tested.getByName(name);

        //then
        assertThat(actual).isNotNull();
        assertThat(actual.isEmpty()).isTrue();
    }

    @Test
    void getByName() {
        //given
        String name = "Lucy";
        List<PersonEntity> result = Collections.singletonList(new PersonEntity());
        when(repository.findAllByNameIgnoreCaseContaining(name)).thenReturn(result);
        when(validator.isValidResult(any())).thenReturn(true);

        //when
        List<Person> actual = tested.getByName(name);

        //then
        assertThat(actual).isNotNull();
        assertThat(actual.isEmpty()).isFalse();
        assertThat(actual.size()).isEqualTo(result.size());
    }

}