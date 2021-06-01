CREATE TABLE book
(
    id    serial       NOT NULL,
    title varchar(255) NOT NULL UNIQUE,
    description text,
    yearOrRelease integer NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (id)
)