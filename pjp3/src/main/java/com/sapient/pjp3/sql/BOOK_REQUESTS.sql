create table BOOK_REQUESTS (
	id INT PRIMARY KEY AUTO_INCREMENT,
	USER_ID INT,
	BOOK_TITLE VARCHAR(200),
	AUTHOR VARCHAR(100),
	REQUESTED_AT DATE,
	FOREIGN KEY(USER_ID) REFERENCES USERS(id)
);
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1001, 15, 'Natural Sheep Care', 'Pat Coleby', '10/12/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1002, 141, 'My Book of Sentences', 'Kumon Publishing', '03/03/2021');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1003, 147, 'Spiritual Growth', 'Sanaya Roman', '14/09/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1004, 130, 'Dead Reckoning', 'Charlaine Harris', '22/05/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1005, 194, 'The Little SAS Book', 'Lora Delwiche', '21/09/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1006, 91, 'RHS Propagating Plants', 'Alan R. Toogood', '11/01/2021');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1007, 10, 'Dialogues and Essays', 'Seneca', '10/02/2021');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1008, 42, 'The Power of Silence', 'Cardinal Robert Sarah', '19/06/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1009, 99, 'Art Forms in Nature: The Prints of Ernst Haeckel', 'Ernst Haeckel', '25/08/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1010, 104, 'Meetings with Remarkable Manuscripts', 'Christopher De Hamel', '02/05/2021');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1011, 18, 'Great Lies to Tell Small Kids', 'Andy Riley', '01/01/2021');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1012, 181, 'Grinding it Out', 'Ray Kroc', '19/06/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1013, 17, 'The Decline and Fall of the Roman Empire', 'Edward Gibbon', '20/04/2021');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1014, 113, 'The Iliad', 'Homer', '30/05/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1015, 184, 'Guilty Pleasures', 'Laurell K. Hamilton', '01/09/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1016, 138, '50 Ways To Draw Your Beautiful, Ordinary Life', 'Flow Magazine', '04/02/2021');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1017, 55, 'The Culture Code', 'Daniel Coyle', '02/07/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1018, 161, 'Walt Disney''s Donald Duck', 'Carl Barks', '19/08/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1019, 39, 'Fingerprints Of The Gods', 'Graham Hancock', '18/10/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1020, 50, 'How To Get Dressed', 'Alison Freer', '09/04/2021');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1021, 194, 'The Unschooling Handbook', 'Mary Griffith', '18/08/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1022, 12, 'This Is Water', 'David Foster Wallace', '02/02/2021');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1023, 35, 'Textbook of Ayurveda', 'Vasant Lad', '19/11/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1024, 33, 'Essentials of Swedish Grammar', 'Ake Viberg', '04/02/2021');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1025, 59, 'The Great Alone', 'Kristin Hannah', '12/06/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1026, 66, 'The Autism Discussion Page on the core challenges of autism', 'Bill Nason', '14/09/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1027, 12, 'Quiet', 'Susan Cain', '29/12/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1028, 118, 'Becoming Myself', 'Irvin D. Yalom', '09/02/2021');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1029, 131, 'The Psychopath Test', 'Jon Ronson', '26/04/2021');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1030, 183, 'The Intent to Live', 'Larry Moss', '13/05/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1031, 66, 'The 104-Storey Treehouse', 'Andy Griffiths', '26/10/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1032, 32, 'Fun Home', 'Alison Bechdel', '24/02/2021');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1033, 97, 'Cronin''S Key Guide to Australian Trees', 'Leonard Cronin', '06/07/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1034, 63, 'Coaching for Performance', 'John Whitmore', '17/05/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1035, 6, 'Bradshaw''s Handbook', 'George Bradshaw', '04/02/2021');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1036, 154, 'The Face of Battle', 'John Keegan', '22/08/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1037, 78, 'The Sea of Tranquility', 'Katja Millay', '25/11/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1038, 54, 'Pioneer Girl', 'Laura Ingalls Wilder', '20/08/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1039, 91, 'The Autism Discussion Page on the core challenges of autism', 'Bill Nason', '27/09/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1040, 155, 'The Psychopath Test', 'Jon Ronson', '08/09/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1041, 34, 'Promise Me You''ll Shoot Yourself: The Mass Suicide of Ordinary Germans in 1945', 'Florian Huber', '29/05/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1042, 116, 'Science in the Kitchen and the Art of Eating Well', 'Pellegrino Artusi', '23/11/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1043, 176, 'Russian Criminal Tattoo Encyclopaedia Volume II', 'Danzig Baldaev', '09/04/2021');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1044, 91, 'Into the Wild', 'Jon Krakauer', '27/09/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1045, 179, 'RHS Propagating Plants', 'Alan R. Toogood', '03/12/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1046, 60, 'Matty Matheson', 'Matty Matheson', '06/01/2021');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1047, 53, 'Paths To God', 'Ram Dass', '20/09/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1048, 105, 'The Power of Silence', 'Cardinal Robert Sarah', '10/09/2020');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1049, 199, 'Harry Potter and the Prisoner of Azkaban', 'J. K. Rowling', '14/02/2021');
insert into BOOK_REQUESTS (id, USER_ID, BOOK_TITLE, AUTHOR, REQUESTED_AT) values (1050, 96, 'Zen: The Art of Simple Living', 'Shunmyo Masuno', '06/08/2020');
