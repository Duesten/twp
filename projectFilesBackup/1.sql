CREATE TABLE users
(
    id bigserial NOT NULL,
    name character varying,
    password character varying,
    CONSTRAINT pk2 PRIMARY KEY (id)
)