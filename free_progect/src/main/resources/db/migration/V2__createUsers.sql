INSERT INTO users
    (email, father_name, first_name, last_name, "password")
VALUES ('user@yandex.ru', 'Victorovich', 'Drannikov', 'Alex',
        '$2y$12$nvWlH8rfQeQ3DzJiSPbCnu815jkr3cJ93LJYrSQOe.wBPvt5cYKI2'),
       ('moderator@yandex.ru', 'Ivanov', 'Saavin', 'Axel',
        '$2y$12$aPojI4JWfgP3fGH9dsLFWe4eZsnkyY4dbDSB7tveqqhDGT7qnz8Jm');

INSERT INTO roles
    (role_name)
VALUES ('ROLE_USER'),
       ('ROLE_MODERATOR');

