package com.example.swcharacters.datasource.model;

import com.example.swcharacters.config.UrlProperties;
import com.example.swcharacters.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SWApiDataSource {

    private final RestTemplate rest;
    private final UrlProperties urls;

    public Optional<Person> importPerson(int id) {
        try {
            Person forObject = rest.getForObject(urls.getPeople(), Person.class, id);
            return Optional.ofNullable(forObject);
        } catch (final HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return Optional.empty();
            }
            throw e;
        }
    }

}
