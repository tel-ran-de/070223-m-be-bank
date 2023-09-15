create table if not exists customer_entity
(
    ID         int          not null AUTO_INCREMENT,
    LOGIN      varchar(100) not null,
    ADDRESS    varchar(100) not null,
    FIRST_NAME varchar(100) not null,
    LAST_NAME  varchar(100) not null,
    EMAIL      varchar(100) not null,
    PRIMARY KEY (ID)
);

CREATE SEQUENCE if not exists "CUSTOMER_ENTITY_SEQ"
    MINVALUE 1
    MAXVALUE 999999999
--     INCREMENT BY 1