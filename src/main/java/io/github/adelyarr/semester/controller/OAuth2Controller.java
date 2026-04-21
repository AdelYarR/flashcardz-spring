package io.github.adelyarr.semester.controller;

import io.github.adelyarr.semester.client.OAuth2GoogleClient;
import io.github.adelyarr.semester.dto.GoogleCodeExchangeResponse;
import io.github.adelyarr.semester.dto.OAuth2UserInfo;
import io.github.adelyarr.semester.security.OAuth2UserAuthentication;
import io.github.adelyarr.semester.service.OAuth2UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static io.github.adelyarr.semester.common.ViewConstants.HUB_VIEW;

@Controller
@AllArgsConstructor
public class OAuth2Controller {

    private OAuth2GoogleClient client;
    private OAuth2UserService oAuth2UserService;

    @GetMapping("/oauth2/authorization/google")
    public String redirectToGoogle() {
        return "redirect:" + client.buildAuthUrl();
    }

    @GetMapping("/login/oauth2/code/google")
    public String handleGoogleCallback(@RequestParam String code, @RequestParam String state,
                                     HttpServletRequest request) {
        GoogleCodeExchangeResponse response = client.exchangeCodeForToken(code);

        OAuth2UserInfo oAuth2UserInfo = client.fetchUserInfo(response.tokenType(), response.accessToken());

        OAuth2UserAuthentication authentication = oAuth2UserService.loadUser(oAuth2UserInfo);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        HttpSession session = request.getSession(true);
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                context
        );

        return "redirect:" + HUB_VIEW;
    }
}
