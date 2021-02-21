BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id serial,
    userName varchar NOT NULL,
    firstName varchar,
    lastName varchar,
    password varchar,
    CONSTRAINT pk_users_id PRIMARY KEY (id)
    );
    
    
INSERT INTO users (id, userName, firstName, lastName, password) VALUES (1, 'Alice', 'Alice', 'Chains', 'password');
INSERT INTO users (id, userName, firstName, lastName, password) VALUES (2, 'Bob', 'Bob', 'Marley', 'password');
INSERT INTO users (id, userName, firstName, lastName, password) VALUES (3, 'Charlie', 'Charlie', 'Parker', 'password');

COMMIT TRANSACTION;