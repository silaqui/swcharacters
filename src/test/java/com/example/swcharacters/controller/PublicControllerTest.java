package com.example.swcharacters.controller;

import com.example.swcharacters.data.model.Person;
import com.example.swcharacters.service.PublicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PublicControllerTest {

    @Mock
    private PublicService publicService;

    @InjectMocks
    public PublicController tested;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldGetCharacter() {
        //given
        int id = 42;
        Optional<Person> result = Optional.of(new Person());
        when(publicService.getById(id)).thenReturn(result);

        //when
        ResponseEntity<Person> actual = tested.getCharacter(id);

        //then
        verify(publicService, times(1)).getById(id);
        verifyNoMoreInteractions(publicService);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldGetCharacter_NotFound() {
        //given
        int id = 42;
        Optional<Person> result = Optional.empty();
        when(publicService.getById(id)).thenReturn(result);

        //when
        ResponseEntity<Person> actual = tested.getCharacter(id);

        //then
        verify(publicService, times(1)).getById(id);
        verifyNoMoreInteractions(publicService);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldSearch() {
        //given
        String name = "Solo";
        List<Person> list = new ArrayList<>();
        when(publicService.getByName(name)).thenReturn(list);

        //when
        ResponseEntity<List<Person>> actual = tested.search(name);

        //then
        verify(publicService, times(1)).getByName(name);
        verifyNoMoreInteractions(publicService);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}