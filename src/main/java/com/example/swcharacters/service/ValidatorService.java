package com.example.swcharacters.service;

import com.example.swcharacters.config.SearchProperties;
import com.example.swcharacters.data.entity.PersonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))

@Service
public class ValidatorService {

    private final SearchProperties searchProperties;

    public boolean isValidResult(PersonEntity person) {
        double maxHeight = searchProperties.getMaxHeight();
        double maxMass = searchProperties.getMaxMass();
        return (maxHeight < 0 || person.getHeight() <= maxHeight)
                && (maxMass < 0 || person.getMass() <= maxMass);
    }
}
