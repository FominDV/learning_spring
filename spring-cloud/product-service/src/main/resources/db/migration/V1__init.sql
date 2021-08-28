create table products
(
    id    bigserial primary key,
    title varchar(60)    not null,
    price numeric(10, 2) not null
);

insert into products
    (title, price)
values ('Milk', 33.4),
       ('Bread', 44),
       ('Pasta', 55.99),
       ('Potato', 193.3),
       ('Appale', 19.5);

