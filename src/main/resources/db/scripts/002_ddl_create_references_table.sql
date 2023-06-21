CREATE TABLE links (
    id SERIAL PRIMARY KEY,
    longName TEXT NOT NULL UNIQUE,
    shortName TEXT UNIQUE,
    site_id INT REFERENCES sites(id)
);