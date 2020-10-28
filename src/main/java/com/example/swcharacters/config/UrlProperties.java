package com.example.swcharacters.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "urls")
@Data
public class UrlProperties {

    private String people;

}
