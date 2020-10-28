package com.example.swcharacters.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = AdminController.class)
class AdminControllerMappingTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminController tested;

    @Test
    void shouldImportCharacter() throws Exception {
        //given
        int inputId = 42;

        //when
        mockMvc.perform(post("/admin/import/character/42"));

        //then
        verify(tested, times(1)).importPerson(inputId);
        verifyNoMoreInteractions(tested);
    }
}