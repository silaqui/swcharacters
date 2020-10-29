package com.example.swcharacters.controller;

import com.example.swcharacters.model.Person;
import com.example.swcharacters.service.PublicService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PublicController.BASE_PATH)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PublicController {

    public static final String BASE_PATH = "/api";
    public static final String BY_ID = "/character/{id}";
    public static final String BY_NAME = "/search/{name}";

    private final PublicService publicService;

    @GetMapping(value = BY_ID)
    @ApiOperation(value = "Import character by ID",
            notes = "Get character from SWApi and save it in local database"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Character not found")})
    public ResponseEntity<Person> getCharacter(@PathVariable("id") int id) {
        Optional<Person> person = publicService.getById(id);
        if (person.isPresent()) {
            return ResponseEntity.of(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = BY_NAME)
    @ApiOperation(value = "Import character by ID",
            notes = "Get character from SWApi and save it in local database"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Return list for matches, can be empty")})
    public ResponseEntity<List<Person>> search(@PathVariable("name") String name) {
        List<Person> list = publicService.getByName(name);
        return ResponseEntity.of(Optional.of(list));
    }
}
