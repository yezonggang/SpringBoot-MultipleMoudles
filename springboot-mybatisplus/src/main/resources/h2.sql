create table STUDENT
(
    ID      INT auto_increment,
    AGE     INT,
    GENDER  VARCHAR,
    CLASSES VARCHAR,
    NAME    VARCHAR,
    constraint STUDENT_PK
        primary key (ID)
);