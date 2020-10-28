package com.example.swcharacters.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sw")
@Data
public class SearchProperties {

    private double maxHeight = -1;
    private double maxMass = -1;

}
