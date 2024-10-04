BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO pets (pet_name, breed, date_of_birth, gender, customer_id, pet_size, vaccination, is_spay_neuter, energy_level, personality, image) VALUES
('Buddy', 'Golden Retriever', '2020-05-15', 'male', 1, 'large', true, true, 'high', 'friendly', 'https://images.pexels.com/photos/2409503/pexels-photo-2409503.jpeg?auto=compress&cs=tinysrgb&w=600'),
('Bella', 'French Bulldog', '2019-03-22', 'female', 1, 'medium', true, true, 'low', 'playful', 'https://images.unsplash.com/photo-1618901977251-d57dd765d2e1?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');
COMMIT TRANSACTION;
