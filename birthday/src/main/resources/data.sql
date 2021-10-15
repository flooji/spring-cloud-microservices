DROP TABLE IF EXISTS BIRTHDAY_PERSONS;

CREATE TABLE BIRTHDAY_PERSONS(
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              first_name VARCHAR(250) NOT NULL,
                              last_name VARCHAR(250) NOT NULL,
                              birthday VARCHAR(250) DEFAULT NULL
);

INSERT INTO BIRTHDAY_PERSONS (first_name, last_name, birthday) VALUES
                                                             ('Florence', 'Dangote', '1999-02-08'),
                                                             ('Tony', 'Gates', '1990-11-08'),
                                                             ('Folrunsho', 'Alakija', '1987-02-17');
