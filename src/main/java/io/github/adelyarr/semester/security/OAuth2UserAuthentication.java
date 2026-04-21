package io.github.adelyarr.semester.security;

import io.github.adelyarr.semester.dto.OAuth2UserInfo;
import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Getter
public class OAuth2UserAuthentication implements Authentication, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String email;
    private final String name;
    private final String sub;
    private boolean authenticated = true;

    public OAuth2UserAuthentication(OAuth2UserInfo userInfo) {
        this.email = userInfo.getEmail();
        this.name = userInfo.getName();
        this.sub = userInfo.getSub();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public @Nullable Object getCredentials() {
        return null;
    }

    @Override
    public @Nullable Object getDetails() {
        return null;
    }

    @Override
    public @Nullable Object getPrincipal() {
        return name;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return email;
    }
}
