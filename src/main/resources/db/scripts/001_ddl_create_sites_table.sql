CREATE TABLE sites (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE,
    login TEXT NOT NULL,
    password TEXT NOT NULL
);