package io.github.adelyarr.semester.repository;

import io.github.adelyarr.semester.entity.CardGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardGroupRepository extends JpaRepository<CardGroup, Long>,
        JpaSpecificationExecutor<CardGroup> {
}
