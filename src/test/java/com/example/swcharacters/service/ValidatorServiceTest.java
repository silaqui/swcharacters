package com.example.swcharacters.service;

import com.example.swcharacters.config.SearchProperties;
import com.example.swcharacters.data.entity.PersonEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ValidatorServiceTest {

    @Mock
    private SearchProperties properties;

    @InjectMocks
    public ValidatorService tested;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldValidResult_valid() {
        //given
        when(properties.getMaxHeight()).thenReturn(10.0);
        when(properties.getMaxMass()).thenReturn(10.0);
        PersonEntity input = new PersonEntity(0, "", 5, 5);

        //when
        boolean actual = tested.isValidResult(input);

        //then
        assertThat(actual).isTrue();
    }

    @Test
    void shouldValidResult_invalidMass() {
        //given
        when(properties.getMaxHeight()).thenReturn(10.0);
        when(properties.getMaxMass()).thenReturn(10.0);
        PersonEntity input = new PersonEntity(0, "", 5, 15);

        //when
        boolean actual = tested.isValidResult(input);

        //then
        assertThat(actual).isFalse();
    }

    @Test
    void shouldValidResult_invalidHeight() {
        //given
        when(properties.getMaxHeight()).thenReturn(10.0);
        when(properties.getMaxMass()).thenReturn(10.0);
        PersonEntity input = new PersonEntity(0, "", 15, 5);

        //when
        boolean actual = tested.isValidResult(input);

        //then
        assertThat(actual).isFalse();
    }

    @Test
    void shouldValidResult_noLimit() {
        //given
        when(properties.getMaxHeight()).thenReturn(-1.0);
        when(properties.getMaxMass()).thenReturn(-1.0);
        PersonEntity input = new PersonEntity(0, "", 0, 0);

        //when
        boolean actual = tested.isValidResult(input);

        //then
        assertThat(actual).isTrue();
    }

}