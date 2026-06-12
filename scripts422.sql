
CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    brand TEXT,
    model TEXT,
    price NUMERIC(12,4),
);

CREATE TABLE people (
    id SERIAL PRIMARY KEY,
    name TEXT,
    age INTEGER,
    driver_license BOOLEAN NOT NULL DEFAULT FALSE,

    car_id INTEGER,

    FOREIGN KEY car_id TEXT REFERENCES people (id)
);