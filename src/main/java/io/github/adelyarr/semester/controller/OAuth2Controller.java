package io.github.adelyarr.semester.controller;

import io.github.adelyarr.semester.client.OAuth2GoogleClient;
import io.github.adelyarr.semester.dto.GoogleCodeExchangeResponse;
import io.github.adelyarr.semester.dto.OAuth2UserInfo;
import io.github.adelyarr.semester.security.OAuth2UserAuthentication;
import io.github.adelyarr.semester.service.OAuth2UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static io.github.adelyarr.semester.common.ViewConstants.HUB_GROUPS_VIEW;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OAuth2Controller {

    private final OAuth2GoogleClient client;
    private final OAuth2UserService oAuth2UserService;

    @GetMapping("/oauth2/authorization/google")
    public String redirectToGoogle() {
        String authUrl = client.buildAuthUrl();

        log.info("Перенаправление на Google OAuth2 URL: {}", authUrl);

        return "redirect:" + authUrl;
    }

    @GetMapping("/login/oauth2/code/google")
    public String handleGoogleCallback(@RequestParam String code, @RequestParam String state,
                                     HttpServletRequest request) {
        log.info("Обработка Google OAuth2 callback - получен code и state");
        log.debug("Code: {}, State: {}", code, state);

        GoogleCodeExchangeResponse response = client.exchangeCodeForToken(code);
        log.info("Токен успешно обменян для пользователя Google");

        OAuth2UserInfo oAuth2UserInfo = client.fetchUserInfo(response.tokenType(), response.accessToken());
        log.debug("Получена информация о пользователе Google: email={}, name={}",
                oAuth2UserInfo.getEmail(), oAuth2UserInfo.getName());

        OAuth2UserAuthentication authentication = oAuth2UserService.loadUser(oAuth2UserInfo);
        log.info("Пользователь OAuth2 аутентифицирован: {}", oAuth2UserInfo.getEmail());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        HttpSession session = request.getSession(true);
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                context
        );

        log.info("SecurityContext сохранен в сессии, перенаправление на {}", HUB_GROUPS_VIEW);

        return "redirect:" + HUB_GROUPS_VIEW;
    }
}
