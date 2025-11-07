CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    name_ VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    email VARCHAR(60) NOT NULL UNIQUE,
    profession VARCHAR(60) NOT NULL,
    birth_date DATE NOT NULL,
    password VARCHAR(30) NOT NULL,
    security_question VARCHAR(60) NOT NULL,
    secret_answer VARCHAR(60) NOT NULL
);
