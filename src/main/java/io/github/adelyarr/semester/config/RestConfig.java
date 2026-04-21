package io.github.adelyarr.semester.config;

import io.github.adelyarr.semester.properties.AppProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Bean
    public RestClient restClient(AppProperties properties) {
        return RestClient.builder().baseUrl(properties.getBaseUrl()).build();
    }
}
