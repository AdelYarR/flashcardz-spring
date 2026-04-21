package io.github.adelyarr.semester.controller;

import io.github.adelyarr.semester.exception.EmailTakenException;
import io.github.adelyarr.semester.model.LoginForm;
import io.github.adelyarr.semester.model.RegistrationForm;
import io.github.adelyarr.semester.service.RegisterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static io.github.adelyarr.semester.common.ViewConstants.*;
import static io.github.adelyarr.semester.common.ViewConstants.REGISTER_VIEW;

@Controller
@AllArgsConstructor
public class AuthController {

    private final RegisterService registerService;

    @GetMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           Model model) {
        if (error != null) {
            model.addAttribute("error", "Неверная почта или пароль");
        }

        model.addAttribute("form", new LoginForm());
        return LOGIN_VIEW;
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("form", new RegistrationForm());
        return REGISTER_VIEW;
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("form") RegistrationForm form,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return REGISTER_VIEW;

        try {
            Long userId = registerService.register(form);
            return "redirect:/" + HUB_VIEW;
        } catch (EmailTakenException e) {
            bindingResult.rejectValue("email", "email.taken", e.getMessage());
            return REGISTER_VIEW;
        }
    }
}
