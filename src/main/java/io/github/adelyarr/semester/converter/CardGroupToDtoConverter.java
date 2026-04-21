package io.github.adelyarr.semester.converter;

import io.github.adelyarr.semester.dto.CardGroupDto;
import io.github.adelyarr.semester.entity.CardGroup;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CardGroupToDtoConverter implements Converter<CardGroup, CardGroupDto> {

    private static final String UNKNOWN_AUTHOR = "-";

    @Override
    public CardGroupDto convert(CardGroup source) {
        if (source == null) {
            return null;
        }

        String authorName = UNKNOWN_AUTHOR;
        if (source.getAuthor() != null) {
            String email = source.getAuthor().getEmail();
            int atIndex = email.indexOf("@");
            authorName = atIndex > 0 ? email.substring(0, atIndex) : email;
        }

        return CardGroupDto.builder()
                .id(source.getId())
                .name(source.getName())
                .authorName(authorName)
                .build();
    }
}
