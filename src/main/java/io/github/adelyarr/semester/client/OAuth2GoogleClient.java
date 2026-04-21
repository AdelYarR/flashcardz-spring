package io.github.adelyarr.semester.client;

import io.github.adelyarr.semester.dto.GoogleCodeExchangeResponse;
import io.github.adelyarr.semester.dto.OAuth2UserInfo;
import io.github.adelyarr.semester.properties.OAuth2Properties;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@AllArgsConstructor
public class OAuth2GoogleClient {

    private final RestClient restClient;
    private final OAuth2Properties properties;

    public GoogleCodeExchangeResponse exchangeCodeForToken(String code) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("client_id", properties.getClientId());
        formData.add("client_secret", properties.getClientSecret());
        formData.add("redirect_uri", properties.getRedirectUri());
        formData.add("grant_type", "authorization_code");

        return restClient
                .post()
                .uri("https://oauth2.googleapis.com/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(formData)
                .retrieve()
                .body(GoogleCodeExchangeResponse.class);
    }

    public OAuth2UserInfo fetchUserInfo(String tokenType, String token) {
        return restClient
                .get()
                .uri("https://www.googleapis.com/oauth2/v3/userinfo")
                .header("Authorization", tokenType + " " + token)
                .retrieve()
                .body(OAuth2UserInfo.class);
    }

    public String buildAuthUrl() {
        return UriComponentsBuilder
                .fromUriString("https://accounts.google.com/o/oauth2/v2/auth")
                .queryParam("client_id", properties.getClientId())
                .queryParam("redirect_uri", properties.getRedirectUri())
                .queryParam("response_type", "code")
                .queryParam("scope", "openid email profile")
                .queryParam("state", "state")
                .build()
                .toUriString();
    }
}
