package com.example.swcharacters.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = PublicController.class)
class PublicControllerMappingTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublicController tested;

    @Test
    void shouldGetCharacter() throws Exception {
        //given
        int input = 12;

        //when
        mockMvc.perform(get("/api/character/12"));

        //then
        verify(tested, times(1)).getCharacter(input);
        verifyNoMoreInteractions(tested);
    }

    @Test
    void shouldSearch() throws Exception {
        //given
        String input = "Han";

        //when
        mockMvc.perform(get("/api/search/Han"));

        //then
        verify(tested, times(1)).search(input);
        verifyNoMoreInteractions(tested);
    }

}