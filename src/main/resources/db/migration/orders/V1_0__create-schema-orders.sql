CREATE SEQUENCE ORDERS_SEQ START WITH 1 INCREMENT BY 50;

CREATE TABLE ORDERS(
    id                         BIGINT NOT NULL PRIMARY KEY,
    name                       VARCHAR
);