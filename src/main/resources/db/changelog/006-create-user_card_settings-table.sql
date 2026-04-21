CREATE TABLE user_card_settings (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    very_easy_interval INT DEFAULT 86400,
    easy_interval INT DEFAULT 43200,
    medium_interval INT DEFAULT 10800,
    hard_interval INT DEFAULT 3600,

    CONSTRAINT fk_settings_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);