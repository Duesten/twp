CREATE TABLE USERS(
    ID bigserial NOT NULL,
    USERNAME character varying,
    PASSWORD character varying,
    CONSTRAINT pk PRIMARY KEY (ID)
);