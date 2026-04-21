CREATE TABLE training_sessions (
    id UUID PRIMARY KEY,
    user_id BIGINT NOT NULL,
    card_group_id BIGINT NOT NULL,
    current_index INT DEFAULT 0,
    max_cards INT NOT NULL,
    max_inactive INT NOT NULL,
    last_access TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_train_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,

    CONSTRAINT fk_train_group FOREIGN KEY (card_group_id) REFERENCES card_groups(id) ON DELETE CASCADE
);