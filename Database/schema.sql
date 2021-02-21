BEGIN TRANSACTION;

DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id serial,
    userName varchar NOT NULL,
    firstName varchar,
    lastName varchar,
    password varchar,
    CONSTRAINT pk_users_id PRIMARY KEY (id)
    );
    
CREATE TABLE post (
    id serial,
    time_stamp TIMESTAMP,
    userId int,
    message varchar,
    CONSTRAINT pk_post_id PRIMARY KEY (id),
    CONSTRAINT fk_users_id FOREIGN KEY (userId) REFERENCES users (id)
    );
    
INSERT INTO users (id, userName, firstName, lastName, password) VALUES (1, 'Alice', 'Alice', 'Chains', 'password');
INSERT INTO users (id, userName, firstName, lastName, password) VALUES (2, 'Bob', 'Bob', 'Marley', 'password');
INSERT INTO users (id, userName, firstName, lastName, password) VALUES (3, 'Charlie', 'Charlie', 'Parker', 'password');


INSERT INTO post (id, time_stamp, userId, message) VALUES (1, '2021-02-21 11:21:59.738', 1, 'I love the weather today.');
INSERT INTO post (id, time_stamp, userId, message) VALUES (2, '2021-02-21 11:21:59.738', 2, 'Darn! We lost!');
INSERT INTO post (id, time_stamp, userId, message) VALUES (3, '2021-02-21 11:21:59.738', 2, 'Good game though.');
INSERT INTO post (id, time_stamp, userId, message) VALUES (4, '2021-02-21 11:21:59.738', 3, 'Im in New York today! Anyone wants to have a coffee?');

COMMIT TRANSACTION;

SELECT * FROM users;
SELECT * FROM post;

SELECT * FROM post WHERE userId = 1;