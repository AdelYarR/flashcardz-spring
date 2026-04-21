package io.github.adelyarr.semester.repository;

import io.github.adelyarr.semester.entity.CardGroup;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class CardGroupSpecifications {

    private CardGroupSpecifications() {}

    public static Specification<CardGroup> nameStartsWith(String search) {
        return (root, query, criteriaBuilder) -> {
            if (search == null || search.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            String pattern = search.toLowerCase() + "%";

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")), pattern
            );
        };
    }

    public static Specification<CardGroup> nameContains(String search) {
        return (root, query, criteriaBuilder) -> {
            if (search == null || search.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            String pattern = "%" + search.toLowerCase() + "%";

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")), pattern
            );
        };
    }

    // Поиск по имени или описанию
    public static Specification<CardGroup> searchByKeyword(String search) {
        return (root, query, criteriaBuilder) -> {
            if (query.getResultType() != Long.class && query.getResultType() != long.class) {
                root.fetch("author", JoinType.LEFT);
                query.distinct(true);
            }

            if (search == null || search.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            String pattern = "%" + search.toLowerCase() + "%";

            Predicate namePredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")), pattern
            );

            Predicate descriptionPredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("description")), pattern
            );

            return criteriaBuilder.or(namePredicate, descriptionPredicate);
        };
    }
}
