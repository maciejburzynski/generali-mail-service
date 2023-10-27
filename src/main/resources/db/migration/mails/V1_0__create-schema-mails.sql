CREATE SEQUENCE MAILS_SEQ START WITH 1 INCREMENT BY 50;

CREATE TABLE MAILS
(
    id                         BIGINT NOT NULL PRIMARY KEY,
    uuid                       VARCHAR,
    receiver                   VARCHAR,
    subject                    VARCHAR,
    content                    VARCHAR,
    saved_at                   TIMESTAMP,
    status                     VARCHAR,
    sent_at                    TIMESTAMP
);