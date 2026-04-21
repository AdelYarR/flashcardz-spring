CREATE TABLE card_tags (
    card_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    PRIMARY KEY (card_id, tag_id),

    CONSTRAINT fk_card FOREIGN KEY (card_id) REFERENCES cards(id) ON DELETE CASCADE,

    CONSTRAINT fk_tag FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
);