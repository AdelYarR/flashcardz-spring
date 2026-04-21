CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    hashed_password TEXT,
    auth_type VARCHAR(25) NOT NULL,
    oauth2_id TEXT UNIQUE,

    CONSTRAINT auth_type_valid CHECK (auth_type IN ('LOCAL', 'GOOGLE')),

    CONSTRAINT must_have_password CHECK (
    auth_type != 'LOCAL' OR (hashed_password IS NOT NULL)
    ),

    CONSTRAINT must_have_oauth2_id CHECK (
    auth_type = 'LOCAL' OR oauth2_id IS NOT NULL
    )
);