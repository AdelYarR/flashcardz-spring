CREATE TABLE training_session_cards (
    id BIGSERIAL PRIMARY KEY,
    session_id UUID NOT NULL,
    card_id BIGINT NOT NULL,
    card_order INT NOT NULL,

    CONSTRAINT fk_train_session FOREIGN KEY (session_id) REFERENCES training_sessions(id) ON DELETE CASCADE,

    CONSTRAINT fk_train_card FOREIGN KEY (card_id) REFERENCES cards(id) ON DELETE CASCADE
);