CREATE TABLE item
(
    id bigserial NOT NULL,
    title character varying,
    description character varying,
    reserved BOOLEAN DEFAULT FALSE,
    CONSTRAINT pk PRIMARY KEY (id)
)