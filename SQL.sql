CREATE TABLE ADMINS(
   admin_id SERIAL PRIMARY KEY,
   username VARCHAR(32) UNIQUE,
   password VARCHAR(32)
);

CREATE TABLE MAJORS(
    major_id VARCHAR(5) PRIMARY KEY
);

ALTER TABLE MAJORS ADD COLUMN major_full_name VARCHAR(255);

CREATE TABLE GROUPS(
    major_id VARCHAR(5)
        REFERENCES MAJORS(MAJOR_ID)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    group_number INT,
    CONSTRAINT group_composite_key PRIMARY KEY(major_id, group_number)
);

CREATE TABLE STUDENTS(
    STUDENT_ID SERIAL PRIMARY KEY,
    student_fname VARCHAR(255),
    student_lname VARCHAR(255),
    year INT,
    username VARCHAR(32) UNIQUE,
    password VARCHAR(32),
    major_id VARCHAR(5),
    group_number INT,
    CONSTRAINT group_composite_key
        FOREIGN KEY(major_id, group_number)
        REFERENCES GROUPS(major_id, group_number)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE CLUBS(
    club_id SERIAL PRIMARY KEY,
    club_name VARCHAR(255)
);

CREATE TABLE Clubs_Students(
    club_id INT REFERENCES CLUBS(CLUB_ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    student_id INT REFERENCES STUDENTS(STUDENT_ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE NEWS(
    news_id SERIAL PRIMARY KEY,
    news_title VARCHAR(255),
    news_description TEXT,
    moderator_id INT REFERENCES STUDENTS(STUDENT_ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Events(
    event_id SERIAL PRIMARY KEY,
    event_name VARCHAR(255),
    event_start_date TIMESTAMP,
    event_end_date TIMESTAMP,
    event_creator_id INT REFERENCES STUDENTS(STUDENT_ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Events_Students(
    event_id INT REFERENCES events(event_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    student_id INT REFERENCES STUDENTS(STUDENT_ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

INSERT INTO MAJORS
VALUES
    ('SE', 'Software Engineer'),
    ('CS', 'Computer Science'),
    ('ITM', 'IT Management'),
    ('MT', 'Media Technologies');

INSERT INTO GROUPS
VALUES ('SE', 1906),('CS', 1905),('SE', 1905),('SE',1904),('MT', 1902);

INSERT INTO STUDENTS(student_fname, student_lname, year, username, password, major_id, group_number)
VALUES
    ('Azizbek', 'Seitmagambetov', 2001, 'aseitmagambetov', 'Qwerty123', 'SE', 1906),
    ('Ibragim', 'Kuanyshbay', 2002, 'ikuanyshbay', 'Qwerty123', 'SE', 1906),
    ('Eskendir', 'Musatai', 2001, 'emusatai', 'Qwerty123', 'MT', 1902);

INSERT INTO admins(admin_id, username, password) VALUES (1,'sazizbek','1234'),
                                                        (2, 'kibragim','1234'),
                                                        (3,'oakan','1234'),
                                                        (4,'oaidana','1234');

INSERT INTO clubs(club_id, club_name) VALUES (101,'KCA AITU'),
                                             (102,'Adal Volunteer Club'),
                                             (103,'AITU TedX Club'),
                                             (104,'Shokugeki Culinary club'),
                                             (105,'AITU SMM Club'),
                                             (106,'AITU Dance Club'),
                                             (107,'Event Club'),
                                             (108,'Evergreen eco club');

----Insert another students for club
INSERT INTO STUDENTS(student_fname, student_lname, year, username, password, major_id, group_number)
VALUES
('Sabina', 'Kenzheyeva', 2001,'skenzheyeva','Qwerty123','CS',1905),
('Darkhan','Baidulat',2002,'dbaibulat','Qwerty123','MT',1902),
('Shyngys','Rakhad',2002,'shrakhad','Qwerty123','SE',1904),
('Serik','Ansat',2001,'sansat','Qwerty123','SE',1905),
('Gulzira','Zhumabayeva',2000,'gzhumabayeva','Qwerty123','CS',1905),
('Aruzhan','Kusainova',2001,'akusainova','Qwerty123','MT',1902),
('Zerdeli','Yesnazar',2000,'zyesnazar','Qwerty123','CS',1905),
('Gulim','Bekilova',2001,'gbekilova','Qwerty123','MT',1902);

----Insert club with presidents
INSERT INTO clubs_students(club_id, student_id) VALUES (101, 4),
                                                       (102, 5),
                                                       (103, 6),
                                                       (104, 7),
                                                       (105, 8),
                                                       (106, 9),
                                                       (107, 10),
                                                       (108, 11);

---- Insert events
INSERT INTO events(event_id, event_name, event_start_date, event_end_date, event_creator_id)
VALUES (201,'K-Pop Dancing Challenge','2020-10-30','2020-11-01',4),
       (202,'Video Editing Master-class','2020-11-02','2020-11-03',3),
       (203,'Halloween: Scary week','2020-10-23','2020-10-31',10),
       (204,'Eco-cleaning of botanic park','2020-10-10','2020-10-13',11),
       (205,'Creating simple Web-page for school pupils:Webinar','2020-11-01','2020-11-03',1);
----Insert events_students
INSERT INTO events_students(event_id, student_id) values (201, 4),
                                                         (202, 3),
                                                         (203, 10),
                                                         (204, 11),
                                                         (205, 1);
----Insert news
INSERT INTO news(NEWS_ID, NEWS_TITLE, NEWS_DESCRIPTION, MODERATOR_ID)
VALUES(301, 'The Roundtable Talk on the topic of “The trinity of languages in the formation of the new essence of the nation”',
       'On November 4th , 2020, the Professors of the program of general disciplines of Astana IT University (Kamiyeva G. K.,
Orazgaliyeva L. M.) and 2nd year students held a roundtable talk on the topic of “The trinity of languages in the formation of the
new essence of the nation” in the online format on the platform Microsoft Teams in order to clarify the Message of the President of
the Republic of Kazakhstan Kasym-Jomart Tokayev to the people of Kazakhstan from 01.09.2020 “Kazakhstan in new conditions: time for action”.

The aim of the Roundtable Talk: focusing on the way to achieve the goals set by the President, one of the ways to improve the quality
of a nation is to widely promote he importance of trilingualism.
Moderator of the Roundtable Talk, 2nd year student Krepak Ivan, dwelled on 11 tasks set by the President in the Address, including
the section “New Quality of the Nation” covering the field of education. Also during the round table, 2nd year students made
presentations on the main tasks of the President’s Address: ” XXI century –the time of highly educated generation» (Abdrakhmanov Renat,
Mauyakhan Aidana). «Modern youth and value of work» (Yegemberdi Galiya, Islam Assel). «The role of trilingualism in the formation
of professional skills» (Mansurova Aigerim, Musagulov Kanat), « Trilingualism is a demand of new time» (Kantayeva Dina, Abayev Yernar).',1),

(302, 'PMI Conference at Astana IT University',
 'The faculty of the “Business and Management” program has participated in the online conference “Project Management during Uncertain Times”
organized by the Project Management Institute (PMI) Kazakhstan Chapter which was held on October 9-10, 2020. Practical project managers and
certified experts in the field of development of project management from Kazakhstan and abroad were the speakers of the event.

On Friday, October 23rd, the President of the PMI Kazakhstan Chapter Yuliya Zhevno has visited and agreed with the University on an
internship organization for AITU students at the PMI office. Moreover, certificates of participants were officially presented to our faculty.',1),

(303,'Distance learning format (information for students)',
'Dear Astana IT University students!

 In order to prevent the spread of coronavirus infection, we organize the educational process in the first trimester of the 2020-2021 academic
year in a distance format.

 For first-year students, festive events and review lectures are planned on September 1 in a distance format. The script of the celebratory
assembly is presented on the website https://astanait.edu.kz/en/aitu-2/ . Scheduled training sessions begin on September 2nd.

 For second-year students, training sessions are scheduled from September 1 in a distance format, according to the schedule.

 We are waiting for you at our meetings and training sessions in a distance format and wish you a successful study!',1);
