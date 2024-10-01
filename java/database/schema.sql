BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, customers, pets, playDate, invitation, playdate_pets, forum cascade;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE customers (
	customer_id SERIAL,
	phone_number varchar(12) NOT NULL,
    email varchar NOT NULL,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    user_id int,
    CONSTRAINT PK_customer_id PRIMARY KEY (customer_id),
    CONSTRAINT FK_customer_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE pets (
    pet_id SERIAL,
    pet_name varchar NOT NULL,
    breed varchar,
    date_of_birth date NOT NULL,
    gender varchar NOT NULL,
    customer_id int,
    pet_size varchar NOT NULL,
    vaccination boolean,
    neutor boolean,
--  potentially put energy level into its own table.
    energy_level varchar,
    personality varchar,
    image varchar,
    CONSTRAINT PK_pet_id PRIMARY KEY (pet_id),
    CONSTRAINT FK_pets_customers FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    CONSTRAINT gender_constraint CHECK (gender IN ('male', 'female')),
    CONSTRAINT pet_size_constraint CHECK (pet_size IN ('small', 'medium', 'large', 'extra large')),
    CONSTRAINT energy_level_constraint CHECK (pet_size IN ('low', 'medium', 'high'))
);

CREATE TABLE playdate (
    playdate_id SERIAL,
    event_location varchar,
    maximum_pets int,
    event_host int,
    event_date date NOT NULL,
    event_time time NOT NULL,
    event_duration int NOT NULL,
    CONSTRAINT PK_playdate_id PRIMARY KEY (playdate_id),
    CONSTRAINT FK_playdate_customers FOREIGN KEY (event_host) REFERENCES customers(customer_id)
);

CREATE TABLE invitation (
    invitation_id SERIAL,
    account_from int NOT NULL,
    account_to int NOT NULL,
    playdate_id int,
    CONSTRAINT PK_invitation_id PRIMARY KEY (invitation_id),
    CONSTRAINT FK_invitation_playdate FOREIGN KEY (playdate_id) REFERENCES playdate(playdate_id)
);

CREATE TABLE playdate_pets (
    playdate_pets_id SERIAL,
    playdate_id int,
    pet_id int,
    CONSTRAINT PK_playdate_pets_id PRIMARY KEY (playdate_pets_id),
    CONSTRAINT FK_playdate_pets_playdate FOREIGN KEY (playdate_id) REFERENCES playdate(playdate_id),
    CONSTRAINT FK_playdate_pets_pets FOREIGN KEY (pet_id) REFERENCES pets(pet_id)
);

CREATE TABLE forum (
    forum_id SERIAL,
    customer_id int,
    forum_title varchar NOT NULL,
    created_date date NOT NULL,
    update_date date NOT NULL,
    comment varchar,
    CONSTRAINT PK_forum_id PRIMARY KEY (forum_id),
    CONSTRAINT FK_forum_customers FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

INSERT INTO users (username, password_hash, role) VALUES ('seth', '$2a$10$gMyKw907TYUR/SdeCPYLgu3j9ZwVe2SZH8xfHNWd8sgHv6ALd3Ivq', 'ROLE_ADMIN');

INSERT INTO customers (phone_number, email, first_name, last_name, user_id) VALUES ('999-999-9999', 'someemail@gmail.com', 'Seth', 'Jacobs', '1');

INSERT INTO playdate (event_location, maximum_pets, event_host, event_date, event_time, event_duration) VALUES ('South Boston Dog Park', '10', '1', '10-02-2024', '1600', '60');
INSERT INTO playdate (event_location, maximum_pets, event_host, event_date, event_time, event_duration) VALUES ('East 1st Dog Park', '15', '1', '10-02-2024', '1700', '120');
INSERT INTO playdate (event_location, maximum_pets, event_host, event_date, event_time, event_duration) VALUES ('Carleton Court Dog Park', '10', '1', '10-02-2024', '1800', '60');
INSERT INTO playdate (event_location, maximum_pets, event_host, event_date, event_time, event_duration) VALUES ('Mansfield Street Dog Park', '20', '1', '10-02-2024', '1700', '120');
INSERT INTO playdate (event_location, maximum_pets, event_host, event_date, event_time, event_duration) VALUES ('RUFF North End Dog Park', '15', '1', '10-02-2024', '1600', '60');

COMMIT TRANSACTION;
