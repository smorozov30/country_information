CREATE TABLE regional_block (
    id SERIAL PRIMARY KEY NOT NULL,
    acronym VARCHAR,
    name VARCHAR
);

CREATE TABLE language (
    id SERIAL PRIMARY KEY NOT NULL,
    iso639_1 VARCHAR,
    iso639_2 VARCHAR,
    name VARCHAR,
    native_name VARCHAR
);

CREATE TABLE currency (
    id SERIAL PRIMARY KEY NOT NULL,
    code VARCHAR,
    name VARCHAR,
    symbol VARCHAR
);

CREATE TABLE translation (
    id SERIAL PRIMARY KEY NOT NULL,
    de VARCHAR,
    es VARCHAR,
    fr VARCHAR,
    ja VARCHAR,
    it VARCHAR,
    br VARCHAR,
    pt VARCHAR,
    nl VARCHAR,
    hr VARCHAR,
    fa VARCHAR
);

CREATE TABLE country (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    population VARCHAR NOT NULL,
    flag VARCHAR (2000) NOT NULL
);

CREATE TABLE domains (
    country_id INTEGER NOT NULL,
    domain VARCHAR NOT NULL,
    FOREIGN KEY (country_id) REFERENCES country (id)
);

CREATE TABLE latlngs (
    country_id INTEGER NOT NULL,
    latlng VARCHAR NOT NULL,
    FOREIGN KEY (country_id) REFERENCES country (id)
);

CREATE TABLE country_currency (
    country_id INTEGER NOT NULL,
    currency_id INTEGER NOT NULL,
    FOREIGN KEY (country_id) REFERENCES country (id),
    FOREIGN KEY (currency_id) REFERENCES currency (id)
);

CREATE TABLE country_language (
    country_id INTEGER NOT NULL,
    language_id INTEGER NOT NULL,
    FOREIGN KEY (country_id) REFERENCES country (id),
    FOREIGN KEY (language_id) REFERENCES language (id)
);

CREATE TABLE country_block (
    country_id INTEGER NOT NULL,
    block_id INTEGER NOT NULL,
    FOREIGN KEY (country_id) REFERENCES country (id),
    FOREIGN KEY (block_id) REFERENCES regional_block (id)
);

CREATE TABLE country_translation (
    country_id INTEGER NOT NULL,
    translation_id INTEGER NOT NULL,
    FOREIGN KEY (country_id) REFERENCES country (id),
    FOREIGN KEY (translation_id) REFERENCES translation (id)
);

CREATE TABLE block_other_acrs (
    regional_block_id INTEGER NOT NULL,
    other_acr VARCHAR NOT NULL,
    FOREIGN KEY (regional_block_id) REFERENCES regional_block (id)
);

CREATE TABLE block_other_names (
    regional_block_id INTEGER NOT NULL,
    other_name VARCHAR NOT NULL,
    FOREIGN KEY (regional_block_id) REFERENCES regional_block (id)
);