package io.github.adelyarr.semester.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static io.github.adelyarr.semester.common.ViewConstants.HUB_VIEW;

@Controller
public class HubController {

    @GetMapping("/hub")
    public String getHub(Model model) {
        return HUB_VIEW;
    }
}
