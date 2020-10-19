DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('TEST', 'TEST@gmail.com', 'TEST');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('TEST', 100001);

INSERT INTO meals ( iduser, datetime, description, calories)
VALUES (100001, now() , 'ADMIN', 2001),
       (100000, now(), 'USER', 1000);


