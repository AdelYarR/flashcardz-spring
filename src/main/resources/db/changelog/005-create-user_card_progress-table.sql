CREATE TABLE user_card_progress (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    card_id BIGINT NOT NULL,
    difficulty VARCHAR(20) NOT NULL,
    last_review TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_progress_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,

    CONSTRAINT fk_progress_card FOREIGN KEY (card_id) REFERENCES cards(id) ON DELETE CASCADE,

    CONSTRAINT check_difficulty CHECK (difficulty IN ('VERY_EASY', 'EASY', 'MEDIUM', 'HARD'))
);