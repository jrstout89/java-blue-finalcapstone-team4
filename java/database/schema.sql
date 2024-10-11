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
	phone_number varchar(12),
    email varchar NOT NULL UNIQUE,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    user_id int NOT NULL,
    CONSTRAINT PK_customer_id PRIMARY KEY (customer_id),
    CONSTRAINT FK_customer_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE pets (
    pet_id SERIAL,
    pet_name varchar NOT NULL,
    breed varchar,
    date_of_birth date NOT NULL,
    gender varchar NOT NULL,
    customer_id int NOT NULL,
    pet_size varchar NOT NULL,
    vaccination boolean,
    is_spay_neuter boolean,
    energy_level varchar,
    personality varchar,
    image varchar,
    CONSTRAINT PK_pet_id PRIMARY KEY (pet_id),
    CONSTRAINT FK_pets_customers FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    CONSTRAINT gender_constraint CHECK (gender IN ('male', 'female')),
    CONSTRAINT pet_size_constraint CHECK (pet_size IN ('small', 'medium', 'large', 'extra large')),
    CONSTRAINT energy_level_constraint CHECK (energy_level IN ('low', 'medium', 'high'))
);

CREATE TABLE playdate (
    playdate_id SERIAL,
    event_title varchar,
    event_location varchar,
    event_address varchar,
    event_latitude float,
    event_longitude float,
    event_coordinate varchar,
    maximum_pets int,
    event_host int NOT NULL,
    event_date date NOT NULL,
    event_time time NOT NULL,
    event_duration int NOT NULL,
    event_description varchar(400),
    event_image varchar,
    CONSTRAINT PK_playdate_id PRIMARY KEY (playdate_id),
    CONSTRAINT FK_playdate_customers FOREIGN KEY (event_host) REFERENCES customers(customer_id)
);

CREATE TABLE invitation (
    invitation_id SERIAL,
    account_from int NOT NULL,
    account_to int NOT NULL,
    playdate_id int NOT NULL,
    invitation_status varchar DEFAULT 'PENDING',
    CONSTRAINT invitation_status_constraint CHECK (invitation_status IN ('PENDING','ACCEPTED','DECLINED')),
    CONSTRAINT PK_invitation_id PRIMARY KEY (invitation_id),
    CONSTRAINT FK_invitation_playdate FOREIGN KEY (playdate_id) REFERENCES playdate(playdate_id),
    CONSTRAINT FK_invitation_account_from FOREIGN KEY (account_from) REFERENCES customers (customer_id),
    CONSTRAINT FK_invitation_account_to FOREIGN KEY (account_to) REFERENCES customers (customer_id),
    CONSTRAINT CK_invitation_not_same_account CHECK (account_from <> account_to)
);

CREATE TABLE playdate_pets (
    playdate_pets_id SERIAL,
    playdate_id int NOT NULL,
    pet_id int NOT NULL,
    CONSTRAINT PK_playdate_pets_id PRIMARY KEY (playdate_pets_id),
    CONSTRAINT FK_playdate_pets_playdate FOREIGN KEY (playdate_id) REFERENCES playdate(playdate_id),
    CONSTRAINT FK_playdate_pets_pets FOREIGN KEY (pet_id) REFERENCES pets(pet_id)
);

CREATE TABLE forum (
    forum_id serial,
    customer_id int NOT NULL,
    forum_title varchar NOT NULL,
    forum_content varchar NOT NULL,
    created_date TIMESTAMP NOT NULL,
    update_date TIMESTAMP NOT NULL,
    CONSTRAINT PK_forum_id PRIMARY KEY (forum_id),
    CONSTRAINT FK_forum_customers FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE comments (
    comment_id SERIAL,
    forum_id int NOT NULL,
    customer_id int NOT NULL,
    comment_content varchar NOT NULL,
    created_date TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT PK_comment_id PRIMARY KEY (comment_id),
    CONSTRAINT FK_comment_forum FOREIGN KEY (forum_id) REFERENCES forum(forum_id),
    CONSTRAINT FK_comment_customers FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

COMMIT TRANSACTION;