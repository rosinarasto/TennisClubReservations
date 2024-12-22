--
-- Surface table definition
--
CREATE TABLE IF NOT EXISTS "Surfaces"
(
    `id`                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `minute_price`      INT NOT NULL,
    `name`              VARCHAR(150) NOT NULL,
    `creation_date`     TIMESTAMP NOT NULL,
    `modification_date` TIMESTAMP NOT NULL
);

--
-- User table definition
--
CREATE TABLE IF NOT EXISTS "Users"
(
    `id`                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `name`              VARCHAR(150) NOT NULL,
    `phone_number`      VARCHAR(150) NOT NULL,
    `password`          VARCHAR(150) NOT NULL,
    `salt`              VARCHAR(150) NOT NULL,
    `creation_date`     TIMESTAMP NOT NULL,
    `modification_date` TIMESTAMP NOT NULL
);

--
-- Court table definition
--
CREATE TABLE IF NOT EXISTS "Courts"
(
    `id`                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `name`              VARCHAR(150) NOT NULL,
    `number`            INT NOT NULL,
    `creation_date`     TIMESTAMP NOT NULL,
    `modification_date` TIMESTAMP NOT NULL,
    `surface_id`        BIGINT REFERENCES "Surfaces"(`id`)
);

--
-- Reservation table definition
--
CREATE TABLE IF NOT EXISTS "Reservations"
(
    `id`                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `creation_date`     TIMESTAMP NOT NULL,
    `modification_date` TIMESTAMP NOT NULL,
    `from`              TIMESTAMP NOT NULL,
    `to`                TIMESTAMP NOT NULL,
    `user_id`           BIGINT REFERENCES "Users"(`id`),
    `court_id`          BIGINT REFERENCES "Courts"(`id`),
    `game_type`         ENUM('SINGLES', 'DOUBLES') NOT NULL
);