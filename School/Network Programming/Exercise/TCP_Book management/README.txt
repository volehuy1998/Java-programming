create database book_management_db;

create table book_tb
(
    id       varchar(10) not null primary key,
    name     varchar(10) not null,
    producer varchar(10) null,
    borrow   tinyint(1)  null
);


   id              name          producer         borrow
    1	            b1	            c1	            1
    2	            b2	            c2	            0
    3	            b3	            c3	            1
    4	            b4	            c4	            0
