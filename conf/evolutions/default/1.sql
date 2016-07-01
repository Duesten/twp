# Users schema

# --- !Ups

CREATE TABLE USER (
    ID bigint(20) NOT NULL AUTO_INCREMENT,
    NAME varchar(255) NOT NULL,
    PASSWORD varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE USER;