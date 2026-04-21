package io.github.adelyarr.semester.controller;

import io.github.adelyarr.semester.converter.CardGroupToDtoConverter;
import io.github.adelyarr.semester.dto.CardGroupDto;
import io.github.adelyarr.semester.entity.CardGroup;
import io.github.adelyarr.semester.service.CardGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import static io.github.adelyarr.semester.common.ViewConstants.HUB_GROUPS_VIEW;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HubController {

    private final CardGroupService cardGroupService;
    private final CardGroupToDtoConverter converter;

    @GetMapping("/hub/groups")
    public String getHubGroups(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(required = false) Optional<String> inputText,
                               Model model) {
        String searchQuery = inputText.orElse("");
        log.info("GET /hub/groups вызван - страница: {}, поисковый запрос: '{}'", page, searchQuery);

        Page<CardGroup> cardGroupPage = cardGroupService.findPaginatedCardGroups(page, searchQuery);
        log.debug("Найдено групп карт: {} из {}", cardGroupPage.getNumberOfElements(), cardGroupPage.getTotalElements());

        List<CardGroupDto> cardGroupDtoList = cardGroupPage.getContent().stream()
                .map(converter::convert)
                .toList();

        model.addAttribute("page", cardGroupPage.getNumber() + 1);
        model.addAttribute("total_pages", cardGroupPage.getTotalPages());
        model.addAttribute("input_text", searchQuery);
        model.addAttribute("card_groups", cardGroupDtoList);

        log.info("Страница хаб-групп возвращена (страница {}/{})",
                cardGroupPage.getNumber() + 1, cardGroupPage.getTotalPages());

        return HUB_GROUPS_VIEW;
    }
}
