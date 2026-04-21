create table users (
    id bigserial primary key,
    email varchar(255) not null unique,
    hashed_password text,
    auth_type varchar(25) not null,
    oauth2_id text unique,

    constraint auth_type_valid check (auth_type in ('LOCAL', 'GOOGLE')),

    constraint must_have_password check (
    auth_type != 'LOCAL' or (hashed_password is not null)
    ),

    constraint must_have_oauth2_id check (
    auth_type = 'LOCAL' or oauth2_id is not null
    )
);