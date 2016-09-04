drop table if exists users CASCADE;
drop SEQUENCE if EXISTS user_id_seq CASCADE;

create sequence user_id_seq start with 1;
CREATE TABLE users
(
    id bigint not null default nextval('user_id_seq') PRIMARY KEY ,
    name character varying,
    password character varying
);

drop table if exists item CASCADE;
drop SEQUENCE if EXISTS item_id_seq CASCADE;
create sequence item_id_seq start with 1;
create table item (
    id bigint not null default nextval('item_id_seq') PRIMARY KEY ,
    created_timestamp timestamp not null,
    deleted_timestamp timestamp,
    title character varying,
    description character varying,
    reserved boolean default FALSE,
    medium character varying,
    creators character varying,
    production character varying,
    year character varying,
    country character varying,
    lang character varying,
    website character varying,
    genre character varying,
    extra character varying
);