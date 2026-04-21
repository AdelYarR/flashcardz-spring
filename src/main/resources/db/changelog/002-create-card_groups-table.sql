CREATE TABLE card_groups (
     id BIGSERIAL PRIMARY KEY,
     author_id BIGINT NOT NULL,
     name VARCHAR(100) NOT NULL,
     description TEXT,
     is_public BOOLEAN DEFAULT FALSE,

     CONSTRAINT fk_group_author FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
);