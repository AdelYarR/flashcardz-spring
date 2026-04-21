package io.github.adelyarr.semester.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record GoogleCodeExchangeResponse(

        @JsonProperty("access_token")
        String accessToken,

        @JsonProperty("id_token")
        String idToken,

        @JsonProperty("expires_in")
        Integer expiresIn,

        @JsonProperty("token_type")
        String tokenType
) {}
