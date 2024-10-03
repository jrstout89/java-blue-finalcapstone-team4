BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO pets (pet_name, breed, date_of_birth, gender, customer_id, pet_size, vaccination, is_spay_neuter, energy_level, personality, image) VALUES
('Buddy', 'Golden Retriever', '2020-05-15', 'male', 1, 'large', true, true, 'high', 'friendly', 'https://unsplash.com/photos/a-golden-retriever-sitting-on-a-sandy-beach-FTbC150wV8Q'),
('Bella', 'French Bulldog', '2019-03-22', 'female', 1, 'medium', true, true, 'low', 'playful', 'https://unsplash.com/photos/brown-and-white-short-coated-small-dog-running-on-green-grass-field-during-daytime-nlli-r8fBxI');

COMMIT TRANSACTION;
