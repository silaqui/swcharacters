package com.example.swcharacters.data.entity;

import com.example.swcharacters.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {

    @Id
    private Integer id;
    private String name;
    private double height;
    private double mass;

    public Person toPerson() {
        return new Person(name, height, mass);
    }

}
