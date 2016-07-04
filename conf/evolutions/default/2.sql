# --- !Ups

CREATE TABLE ITEM (
    ID bigserial NOT NULL,
    TITLE character varying,
    DESCRIPTION character varying,
    RESERVED BOOLEAN DEFAULT FALSE,
    CONSTRAINT pk PRIMARY KEY (ID)
);

# --- !Downs

DROP TABLE ITEM;