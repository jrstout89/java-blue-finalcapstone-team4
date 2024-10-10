BEGIN TRANSACTION;

INSERT INTO users (username, password_hash, role) VALUES ('bark_simpson', '$2a$10$gMyKw907TYUR/SdeCPYLgu3j9ZwVe2SZH8xfHNWd8sgHv6ALd3Ivq', 'ROLE_ADMIN');
INSERT INTO users (username, password_hash, role) VALUES ('pupu', '$2a$10$gMyKw907TYUR/SdeCPYLgu3j9ZwVe2SZH8xfHNWd8sgHv6ALd3Ivq', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('jon', '$2a$10$gMyKw907TYUR/SdeCPYLgu3j9ZwVe2SZH8xfHNWd8sgHv6ALd3Ivq', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('athena', '$2a$10$gMyKw907TYUR/SdeCPYLgu3j9ZwVe2SZH8xfHNWd8sgHv6ALd3Ivq', 'ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO customers (phone_number, email, first_name, last_name, user_id) VALUES ('999-999-9999', 'someemail@gmail.com', 'Seth', 'Jacobs', '1');
INSERT INTO customers (phone_number, email, first_name, last_name, user_id) VALUES ('999-999-9998', 'anotheremail@yahoo.com', 'You Yuan', 'Gao', '2');
INSERT INTO customers (phone_number, email, first_name, last_name, user_id) VALUES ('999-999-9997', 'randomemail@bing.com', 'Jonathon', 'Stout', '3');
INSERT INTO customers (phone_number, email, first_name, last_name, user_id) VALUES ('999-999-9996', 'noemailhere@outlook.com', 'Claudia', 'Carmona', '4');

INSERT INTO playdate (event_title, event_location, event_address, event_latitude, event_longitude, maximum_pets, event_host, event_date, event_time, event_duration, event_description, event_image) VALUES ('Paw-tastic Playdate at the Park', 'South Boston Dog Park', '1280 Columbia Rd, Boston, MA 02127', '42.33039' , '-71.04784', '10', '1', '10-12-2024', '1200', '120', 'Join us for a fun-filled afternoon at South Boston Dog Park! Let your furry friends socialize, romp around, and enjoy some fresh air. This event is perfect for dogs of all sizes. Bring your favorite toys, and don’t forget the water bowl! Meet fellow pet parents and share tips while your pups play their hearts out.', 'https://lh5.googleusercontent.com/p/AF1QipNA6GjALmYtD0IEiHTvLh1_NtJLgByLEFlyfpzj=w114-h114-n-k-no');
INSERT INTO playdate (event_title, event_location, event_address, event_latitude, event_longitude, maximum_pets, event_host, event_date, event_time, event_duration, event_description, event_image) VALUES ('Yappy Hour: Doggie Meetup', 'East 1st Dog Park', '768-764 E 1st St, Boston, MA 02127', '42.33822', '-71.0319', '20', '3', '10-16-2024', '1700', '120', 'It’s time for a Yappy Hour! Bring your pups to East 1st Dog Park for an evening of tail-wagging fun. Enjoy some snacks, make new friends, and let your dogs play freely in a safe environment. We’ll have some games and prizes for the best tricks! Don’t miss out on this paw-some social event!', 'https://lh3.googleusercontent.com/p/AF1QipN9zSRPTCrP7qTc958e8gULxw-oXZJjOcBpqBat=s680-w680-h510');
INSERT INTO playdate (event_title, event_location, event_address, event_latitude, event_longitude, maximum_pets, event_host, event_date, event_time, event_duration, event_description, event_image) VALUES ('Fetch & Friends: Weekend Play Date', 'Carleton Court Dog Park', 'SW Corridor Path, Boston, MA 02116', '42.34913', '-71.07363', '20', '2', '10-19-2024', '1000', '240', 'Start your weekend off right with a game of fetch at Carleton Court Dog Park! Bring your favorite fetch toys, and let your dogs run wild. This meetup is all about letting our pets unleash their energy and make new friends. A great way to connect with fellow dog lovers in the community!', 'https://lh3.googleusercontent.com/p/AF1QipOGaRlO3MMBjwCySCns2_PU152nZ0E8IUDv3cbv=s680-w680-h510');
INSERT INTO playdate (event_title, event_location, event_address, event_latitude, event_longitude, maximum_pets, event_host, event_date, event_time, event_duration, event_description, event_image) VALUES ('Howl-O-Ween Costume Party', 'Mansfield Street Dog Park', '488-556 Lincoln St, Boston, MA 02134', '42.35754', '-71.12943', '30', '1', '10-30-2024', '1800', '120', 'Get ready for a howling good time at our Howl-O-Ween Costume Party! Dress your pup up in their best costume and join us at Mansfield Street Dog Park for a day of fun, games, and contests. Prizes will be awarded for the best costumes! Bring your camera for some unforgettable photo ops!', 'https://wpcdn.us-midwest-1.vip.tn-cloud.net/www.honolulumagazine.com/content/uploads/data-import/16059c64/halloween-dog-costume-witch.jpg');
INSERT INTO playdate (event_title, event_location, event_address, event_latitude, event_longitude, maximum_pets, event_host, event_date, event_time, event_duration, event_description, event_image) VALUES ('Senior Dog Social: Golden Years Gathering', 'RUFF North End Dog Park', 'BOST-0301936000, Boston, MA 02113', '42.36549', '-71.05297', '15', '4', '11-02-2024', '1000', '120', 'Join us for a special play date dedicated to our senior pups at RUFF North End Dog Park. This gentle gathering is perfect for older dogs to socialize at their own pace. Enjoy some shade and relax while your furry friends mingle. We’ll have light refreshments for humans and special treats for our golden oldies!', 'https://lh3.googleusercontent.com/p/AF1QipPnl-LD1JHvbNJ_KcQ_6CkjFELvN1JS5Q9Eu_IE=s680-w680-h510');

INSERT INTO pets (pet_name, breed, date_of_birth, gender, customer_id, pet_size, vaccination, is_spay_neuter, energy_level, personality, image) VALUES
('Buddy', 'Golden Retriever', '2020-05-15', 'male', '3', 'large', true, true, 'high', 'friendly', 'https://images.pexels.com/photos/2409503/pexels-photo-2409503.jpeg?auto=compress&cs=tinysrgb&w=600'),
('Bella', 'French Bulldog', '2019-03-22', 'female', '3', 'medium', true, true, 'low', 'playful', 'https://images.unsplash.com/photo-1618901977251-d57dd765d2e1?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');
INSERT INTO pets (pet_name, breed, date_of_birth, gender, customer_id, pet_size, vaccination, is_spay_neuter, energy_level, personality, image) VALUES ('PuPu', 'Husky', '2022-06-07', 'male', '2', 'large', 'true', 'true', 'high', 'energetic', 'https://fb-strapi.s3.amazonaws.com/large_Fit_Bark_husky_running_grass_outdoor_field_bef3f605ac.jpg');
INSERT INTO pets (pet_name, breed, date_of_birth, gender, customer_id, pet_size, vaccination, is_spay_neuter, energy_level, personality, image) VALUES ('Karma', 'Rottweiler', '2019-11-20', 'female', '1', 'large', 'true', 'true', 'high', 'friendly', 'https://cdn.britannica.com/70/234470-050-F25D5205/Rottweiler-dog.jpg');
INSERT INTO pets (pet_name, breed, date_of_birth, gender, customer_id, pet_size, vaccination, is_spay_neuter, energy_level, personality, image) VALUES ('Dexter', 'Pug', '2024-09-02', 'male', '1', 'small', 'true', 'false', 'low', 'playful', 'https://i.pinimg.com/736x/1d/18/26/1d1826bf4f9e0850f32e3cca45dd7797.jpg');
INSERT INTO pets (pet_name, breed, date_of_birth, gender, customer_id, pet_size, vaccination, is_spay_neuter, energy_level, personality, image) VALUES ('Athena', 'American Staffordshire Terrier', '2020-10-03', 'female', '4', 'medium', 'true', 'true', 'low', 'friendly', 'https://espree.com/sites/default/files/2019-10/AmericanStaffordshireTerrier.png');

INSERT INTO forum (customer_id, forum_title, forum_content, created_date, update_date)
VALUES ('4', 'Favorite Famous Pets', 'How about we talk about our favorite famous pets! I will get us started with Milo from Milo and Otis. Who is yours?', '2024-10-08 09:00:00+00', '2024-10-08 09:00:00+00');

INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('1', '2', 'You cannot go wrong with Lassie. Classic.', '2024-10-08 09:10:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('1', '1', 'Every pet that Ace Ventura had!!', '2024-10-08 09:15:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('1', '3', 'I like that ferret or mink or whatever from that movie where it fights the cobras. Remy Timmy or something like that?', '2024-10-08 09:20:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('1', '1', 'Rikki-Tikki-Tavi? I loved that movie when I was a kid!! I need to go watch that movie now. Thanks for reminding me it exists!! Oh and I think he was a mongoose.', '2024-10-08 09:25:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('1', '2', 'Was Rikki-Tikki-Tavi a pet though? I thought he was a wild animal?', '2024-10-08 09:30:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('1', '4', 'Yeah I thought he was a wild animal too.', '2024-10-08 09:35:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('1', '1', 'I mean, he kinda was and kinda was not. He was a wild animal until that family saved him from a flood. Then they adopted him as a pet, and he became the family protector. That is where the cobra fights came from that scared me as a kid, especially the one in the bathroom at night lol.', '2024-10-08 09:40:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('1', '3', 'Those cobras traumatized me. I could not sleep for days after watching that.', '2024-10-08 09:45:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('1', '2', 'Great, now I am not going to sleep for days thinking about cobras trying to get me in my sleep. Thanks for that...', '2024-10-08 09:50:00+00');


INSERT INTO forum (customer_id, forum_title, forum_content, created_date, update_date)
VALUES ('3', 'What Are Your Favorite Dog Breeds?', 'What are your favorite dog breeds and why? I’m a huge fan of Golden Retrievers! They’re so friendly and loyal.', '2024-10-09 10:00:00+00', '2024-10-09 10:00:00+00');

INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('2', '1', 'I love Rottweilers! They’re so playful and have such great personalities.', '2024-10-09 10:05:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('2', '3', 'Poodles are my favorite! They’re super smart and hypoallergenic.', '2024-10-09 10:10:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('2', '2', 'I’m all about Huskies! Their energy is contagious.', '2024-10-09 10:15:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('2', '4', 'Oh I love huskies too! They are so cute. Like little wolves.', '2024-10-09 10:20:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('2', '1', 'I will agree that huskies are great too. I still love my rottie though.', '2024-10-09 10:25:00+00');


INSERT INTO forum (customer_id, forum_title, forum_content, created_date, update_date)
VALUES ('2', 'Tips for New Dog Owners', 'What tips do you have for new dog owners? I just adopted a puppy and could use all the advice I can get!', '2024-10-09 10:30:00+00', '2024-10-09 10:30:00+00');

INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('3', '1', 'Make sure to socialize them early! It really helps with their behavior.', '2024-10-09 10:35:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('3', '3', 'Consistency in training is key! Stick to a routine.', '2024-10-09 10:40:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('3', '1', 'I agree. Having a routine is really important. If you keep to a very consistent schedule, the dog will figure it out faster than you think they will.', '2024-10-09 10:45:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('3', '4', 'Don’t forget to puppy-proof your home!', '2024-10-09 10:50:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('3', '2', 'What does puppy-proofing entail? This is the first dog I have ever owned, so I want to be sure that I do not overlook something important.', '2024-10-09 10:55:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('3', '3', 'You want to try to keep cords hidden away or inaccessible to your puppy. They like to chew on things, and you do not want them chewing on something plugged into the wall. On that note, make sure that you keep things off of the floor that you do not want them chewing on. If you have a pair of shoes on the floor and do not keep an eye on them, I promise that you will find them gnawed on at some point. Make sure that you do not set them up for failure.', '2024-10-09 11:00:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('3', '4', 'Yes to that! They will chew on everything if you let them. Keep toys around for them to focus their attention on instead of your shoes.', '2024-10-09 11:05:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('3', '1', 'Can confirm. I lost two pairs of shoes before I learned my lesson...', '2024-10-09 11:10:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('3', '2', 'RIP your shoes.', '2024-10-09 11:15:00+00');


INSERT INTO forum (customer_id, forum_title, forum_content, created_date, update_date)
VALUES ('3', 'Funniest Pet Moments', 'Let’s share our funniest pet moments! My dog once chased his tail and crashed into the couch. He came to a dead stop for a few seconds, slowly looked up at me, stared for a few more seconds, and then farted loudly before promptly returning to chasing his tail.', '2024-10-09 11:30:00+00', '2024-10-09 11:30:00+00');

INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('4', '1', 'My cat once got scared of her own reflection. It was hilarious!', '2024-10-09 11:35:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('4', '3', 'That is awesome. I once scared my cat with a plastic bag on the floor. It was a big takeout bag that my cat was slowly creeping up to investigate. As soon as he poked his head in the bag, I shook it. He jumped like three feet in the air without bending his legs at all. My girlfriend and I laughed about it for an hour!', '2024-10-09 11:40:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('4', '1', 'Lol!!! Amazing.', '2024-10-09 11:45:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('4', '2', 'I caught my dog trying to “help” me clean up crumbs—by eating them.', '2024-10-09 11:50:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('4', '3', 'Hey, everyone has to have a job.', '2024-10-09 11:55:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('4', '2', 'Yep. And she is good at it too!', '2024-10-09 12:00:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('4', '4', 'I have a parrot that mimics the sound of the vacuum. It’s too funny!', '2024-10-09 12:05:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('4', '1', 'Wait, really? I knew that parrots could mimic some really crazy stuff, but that seems insane to me. Does it do the vacuum sound all the time, when you go to vacuum, or just at completely random times?', '2024-10-09 12:10:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('4', '4', 'It is completely random. At all hours. Sometimes I will be trying to sleep at night and will hear the vacuum fire up out of nowhere. It used to scare me so bad I could not go back to sleep, but it has happened so much now that I feel pretty desensitized to it.', '2024-10-09 12:15:00+00');
INSERT INTO comments (forum_id, customer_id, comment_content, created_date)
VALUES ('4', '2', 'Oh my god, that sounds like nightmare fuel. I would never sleep again if I heard that in the middle of the night.', '2024-10-09 12:20:00+00');

COMMIT TRANSACTION;
