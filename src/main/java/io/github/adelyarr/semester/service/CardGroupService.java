package io.github.adelyarr.semester.service;

import io.github.adelyarr.semester.entity.CardGroup;
import io.github.adelyarr.semester.repository.CardGroupRepository;
import io.github.adelyarr.semester.repository.CardGroupSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardGroupService {

    private static final int PAGE_SIZE = 12;

    private final CardGroupRepository cardGroupRepository;

    public Page<CardGroup> findPaginatedCardGroups(int page, String searchQuery) {
        log.info("Поиск пагинированных групп карт - страница: {}, поисковый запрос: {}, размер страницы: {}",
                page, searchQuery, PAGE_SIZE);

        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);

        Specification<CardGroup> specification = CardGroupSpecifications.searchByKeyword(searchQuery);

        Page<CardGroup> result = cardGroupRepository.findAll(specification, pageable);

        log.info("Результат поиска: найдено {} элементов, всего страниц: {}, всего элементов: {}",
                result.getNumberOfElements(), result.getTotalPages(), result.getTotalElements());

        return result;
    }
}
