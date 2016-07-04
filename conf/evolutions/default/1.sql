# --- !Ups

CREATE TABLE "USER" (
    ID bigserial NOT NULL,
    username character varying,
    PASSWORD character varying,
    CONSTRAINT pk PRIMARY KEY (ID)
);

# --- !Downs

DROP TABLE "USER";