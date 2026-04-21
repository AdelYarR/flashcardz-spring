CREATE TABLE cards (
    id BIGSERIAL PRIMARY KEY,
    group_id BIGINT NOT NULL,
    question TEXT NOT NULL,
    answer TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_card_group FOREIGN KEY (group_id) REFERENCES card_groups(id) ON DELETE CASCADE
);