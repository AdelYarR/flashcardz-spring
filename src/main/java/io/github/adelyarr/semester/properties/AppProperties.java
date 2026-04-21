package io.github.adelyarr.semester.properties;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "app")
@Validated
@NoArgsConstructor
@Getter
@Setter
public class AppProperties {

    @NotEmpty
    private String baseUrl;
}
