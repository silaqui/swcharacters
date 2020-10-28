package com.example.swcharacters.controller;

import com.example.swcharacters.model.Person;
import com.example.swcharacters.service.AdminService;
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

    static final String BASE_PATH = "/admin";
    static final String CHARACTER = "/import/character/{id}";

    private final AdminService adminService;

    @PostMapping(value = CHARACTER)
    public ResponseEntity importPerson(@PathVariable("id") Integer id) {
        Optional<Person> person = adminService.importPerson(id);
        if (person.isPresent()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
