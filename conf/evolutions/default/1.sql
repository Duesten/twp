CREATE TABLE USERS(
    ID bigserial NOT NULL,
    username character varying,
    PASSWORD character varying,
    CONSTRAINT pk PRIMARY KEY (ID)
);