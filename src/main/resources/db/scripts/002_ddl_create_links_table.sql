CREATE TABLE links (
    id SERIAL PRIMARY KEY,
    long_name TEXT NOT NULL UNIQUE,
    short_name TEXT NOT NULL UNIQUE,
    total INT
);