package io.github.adelyarr.semester.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OAuth2UserInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String sub;
    private String email;
    private String name;
}
