INSERT INTO base_time_entity
    (id)
VALUES (1),
       (2);

INSERT INTO users
    (id,email, father_name, first_name, last_name, "password")
VALUES (1,'user@yandex.ru', 'Victorovich', 'Drannikov', 'Alex',
        '$2y$12$nvWlH8rfQeQ3DzJiSPbCnu815jkr3cJ93LJYrSQOe.wBPvt5cYKI2'),
       (2,'moderator@yandex.ru', 'Ivanov', 'Saavin', 'Axel',
        '$2y$12$aPojI4JWfgP3fGH9dsLFWe4eZsnkyY4dbDSB7tveqqhDGT7qnz8Jm');

INSERT INTO roles
    (id,role_name)
VALUES (3,'ROLE_USER'),
       (4,'ROLE_MODERATOR');

