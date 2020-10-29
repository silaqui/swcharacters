package com.example.swcharacters.controller;

import com.example.swcharacters.model.Person;
import com.example.swcharacters.service.AdminService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(AdminController.BASE_PATH)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    public static final String BASE_PATH = "/admin";
    public static final String IMPORT_CHARACTER = "/import/character/{id}";

    private final AdminService adminService;

    @PostMapping(value = IMPORT_CHARACTER)
    @ApiOperation(value = "Import character by ID",
            notes = "Get character from SWApi and save it in local database"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - character found in SWApi and saved"),
            @ApiResponse(code = 404, message = "Character not found")})
    public ResponseEntity importPerson(@PathVariable("id") Integer id) {
        Optional<Person> person = adminService.importPerson(id);
        if (person.isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
