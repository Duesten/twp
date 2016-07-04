CREATE TABLE item(
    ID bigserial NOT NULL,
    TITLE character varying,
    DESCRIPTION character varying,
    RESERVED BOOLEAN DEFAULT FALSE,
    CONSTRAINT pk PRIMARY KEY (ID)
);