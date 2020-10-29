package com.example.swcharacters.controller;

import com.example.swcharacters.data.model.Person;
import com.example.swcharacters.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    public AdminController tested;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldImportPerson() {
        //given
        int inputId = 42;
        when(adminService.importPerson(inputId)).thenReturn(Optional.of(new Person()));

        //when
        ResponseEntity actual = tested.importPerson(inputId);

        //then
        verify(adminService, times(1)).importPerson(inputId);
        verifyNoMoreInteractions(adminService);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldImportPerson_NotFound() {
        //given
        int inputId = 42;

        //when
        ResponseEntity actual = tested.importPerson(inputId);

        //then
        verify(adminService, times(1)).importPerson(inputId);
        verifyNoMoreInteractions(adminService);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


}