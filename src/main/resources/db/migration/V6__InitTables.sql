CREATE TABLE movie1
(
    id           BIGSERIAL PRIMARY KEY,
    name         TEXT NOT NULL,
    release_date DATE NOT NULL,
    unique (name)
);

CREATE TABLE user6
(
    id        BIGSERIAL PRIMARY KEY,
    firstname TEXT,
    lastname  TEXT,
    password  TEXT,
    email     TEXT,
    creatAt   DATE,
    role      TEXT,
    unique (email)
);

CREATE TABLE actor
(
    id   BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    unique (name)
);