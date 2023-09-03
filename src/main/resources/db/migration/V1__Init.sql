CREATE TABLE movie
(
    id           BIGSERIAL PRIMARY KEY,
    name         TEXT NOT NULL,
    release_date DATE NOT NULL,
    unique (name)
);

CREATE TABLE users
(
    id        BIGSERIAL PRIMARY KEY,
    firstname TEXT,
    lastname  TEXT,
    password  TEXT,
    email     TEXT,
    creatAt   TEXT,
    role      TEXT,
    unique (email)
);

CREATE TABLE actor
(
    id   BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    unique (name)
);
