package io.github.adelyarr.semester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SemesterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SemesterApplication.class, args);
    }
}
