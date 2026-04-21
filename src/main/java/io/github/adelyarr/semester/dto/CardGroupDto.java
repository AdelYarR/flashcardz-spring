package io.github.adelyarr.semester.dto;

import lombok.Builder;

@Builder
public record CardGroupDto (
        Long id,
        String name,
        String authorName
) {}
