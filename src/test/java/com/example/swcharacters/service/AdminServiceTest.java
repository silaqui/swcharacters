package com.example.swcharacters.service;

import com.example.swcharacters.data.SWApiDataSource;
import com.example.swcharacters.data.StarWarsRepository;
import com.example.swcharacters.data.entity.PersonEntity;
import com.example.swcharacters.data.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AdminServiceTest {

    @Mock
    private StarWarsRepository repository;
    @Mock
    private SWApiDataSource api;

    @InjectMocks
    public AdminService tested;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldImportPerson() {
        //given
        int id = 42;
        Person entity = new Person();
        Optional<Person> result = Optional.of(entity);
        when(api.importPerson(id)).thenReturn(result);
        PersonEntity personEntity = PersonEntity.fromPerson(id, entity);

        //when
        Optional<Person> actual = tested.importPerson(id);

        //then
        verify(api, times(1)).importPerson(id);
        verifyNoMoreInteractions(api);
        verify(repository, times(1)).save(personEntity);
        verifyNoMoreInteractions(repository);
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get()).isEqualTo(entity);
    }

    @Test
    void shouldImportPerson_NotFound() {
        //given
        int id = 42;
        Person entity = new Person();
        Optional<Person> result = Optional.empty();
        when(api.importPerson(id)).thenReturn(result);

        //when
        Optional<Person> actual = tested.importPerson(id);

        //then
        verify(api, times(1)).importPerson(id);
        verifyNoMoreInteractions(api);
        verifyNoInteractions(repository);
        assertThat(actual.isPresent()).isFalse();
    }

}