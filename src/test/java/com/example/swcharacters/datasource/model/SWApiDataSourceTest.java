package com.example.swcharacters.datasource.model;

import com.example.swcharacters.config.UrlProperties;
import com.example.swcharacters.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SWApiDataSourceTest {

    @Mock
    private RestTemplate rest;
    @Mock
    private UrlProperties urls;

    @InjectMocks
    public SWApiDataSource tested;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldImportPerson() {
        //given
        String url = "url/{id}";
        int id = 42;
        Person person = new Person();
        when(urls.getPeople()).thenReturn(url);
        when(rest.getForObject(url, Person.class, id)).thenReturn(person);

        //when
        Optional<Person> actual = tested.importPerson(id);

        //then
        verify(rest, times(1)).getForObject(url, Person.class, id);
        verifyNoMoreInteractions(rest);
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get()).isEqualTo(person);
    }

    @Test
    void shouldImportPerson_NotFound() {
        //given
        String url = "url/{id}";
        int id = 42;
        HttpClientErrorException notFound = new HttpClientErrorException(HttpStatus.NOT_FOUND);
        when(urls.getPeople()).thenReturn(url);
        when(rest.getForObject(url, Person.class, id)).thenThrow(notFound);

        //when
        Optional<Person> actual = tested.importPerson(id);

        //then
        verify(rest, times(1)).getForObject(url, Person.class, id);
        verifyNoMoreInteractions(rest);
        assertThat(actual.isPresent()).isFalse();
    }

    @Test
    void shouldImportPerson_NotCatchUnexpectedError() {
        //given
        String url = "url/{id}";
        int id = 42;
        HttpClientErrorException notFound = new HttpClientErrorException(HttpStatus.FORBIDDEN);
        when(urls.getPeople()).thenReturn(url);
        when(rest.getForObject(url, Person.class, id)).thenThrow(notFound);

        //when
        assertThrows(HttpClientErrorException.class, () ->
                tested.importPerson(id));

        //then
    }

}