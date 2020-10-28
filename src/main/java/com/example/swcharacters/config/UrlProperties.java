package com.example.swcharacters.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Component
@ConfigurationProperties(prefix = "urls")
@Data
@Validated
public class UrlProperties {

    @NotEmpty
    private String people;

}
