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
    created_date TIMESTAMP NOT NULL,
    CONSTRAINT PK_comment_id PRIMARY KEY (comment_id),
    CONSTRAINT FK_comment_forum FOREIGN KEY (forum_id) REFERENCES forum(forum_id),
    CONSTRAINT FK_comment_customers FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

INSERT INTO users (username, password_hash, role) VALUES ('seth', '$2a$10$gMyKw907TYUR/SdeCPYLgu3j9ZwVe2SZH8xfHNWd8sgHv6ALd3Ivq', 'ROLE_ADMIN');
INSERT INTO customers (phone_number, email, first_name, last_name, user_id) VALUES ('999-999-9999', 'someemail@gmail.com', 'Seth', 'Jacobs', '1');

INSERT INTO playdate (event_title, event_location, event_address, maximum_pets, event_host, event_date, event_time, event_duration, event_description, event_image) VALUES ('Dog Day Afternoon','South Boston Dog Park', '1280 Columbia Rd, Boston, MA 02127', '10', '1', '10-02-2024', '1600', '60', 'this is a description', 'https://lh5.googleusercontent.com/p/AF1QipNA6GjALmYtD0IEiHTvLh1_NtJLgByLEFlyfpzj=w114-h114-n-k-no');
INSERT INTO playdate (event_title, event_location, event_address, maximum_pets, event_host, event_date, event_time, event_duration, event_description, event_image) VALUES ('Paw & Pals', 'East 1st Dog Park', '768-764 E 1st St, Boston, MA 02127', '15', '1', '10-02-2024', '1700', '120', 'this is a description', 'https://lh3.googleusercontent.com/p/AF1QipN9zSRPTCrP7qTc958e8gULxw-oXZJjOcBpqBat=s680-w680-h510');
INSERT INTO playdate (event_title, event_location, event_address, maximum_pets, event_host, event_date, event_time, event_duration, event_description, event_image) VALUES ('Puppy Friends', 'Carleton Court Dog Park', 'SW Corridor Path, Boston, MA 02116', '10', '1', '10-02-2024', '1800', '60', 'this is a description', 'https://lh3.googleusercontent.com/p/AF1QipOGaRlO3MMBjwCySCns2_PU152nZ0E8IUDv3cbv=s680-w680-h510');
INSERT INTO playdate (event_title, event_location, event_address, maximum_pets, event_host, event_date, event_time, event_duration, event_description, event_image) VALUES ('Wag the Dog', 'Mansfield Street Dog Park', '488-556 Lincoln St, Boston, MA 02134', '20', '1', '10-02-2024', '1700', '120', 'this is a description', 'https://lh3.googleusercontent.com/p/AF1QipNXVNEDCcLnnzLXyX1_LlfjtA-tAlREipOqZ9oD=s680-w680-h510');
INSERT INTO playdate (event_title, event_location, event_address, maximum_pets, event_host, event_date, event_time, event_duration, event_description, event_image) VALUES ('Furry Friday', 'RUFF North End Dog Park', 'BOST-0301936000, Boston, MA 02113', '15', '1', '10-02-2024', '1600', '60', 'this is a description', 'https://lh3.googleusercontent.com/p/AF1QipPnl-LD1JHvbNJ_KcQ_6CkjFELvN1JS5Q9Eu_IE=s680-w680-h510');

INSERT INTO forum (customer_id, forum_title, forum_content, created_date, update_date) values ('1', 'Favorite Famous Pets', 'How about we talk about our favorite famous pets!! I will get us started with Lassie. Who is yours?', NOW(), NOW());

INSERT INTO comments (forum_id, customer_id, comment_content, created_date) VALUES ('1', '1', 'I love Milo from Milo and Otis.', NOW());

COMMIT TRANSACTION;