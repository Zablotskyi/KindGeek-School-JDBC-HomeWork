drop database authorsbooks_kindGeek_school_JDBC_homeWork;
create database authorsbooks_kindGeek_school_JDBC_homeWork;
use authorsbooks_kindGeek_school_JDBC_homeWork;
set char set utf8;

create table authors(
id int auto_increment primary key,
name varchar(100),
lastNmae varchar(100)
);

create table books(
id int auto_increment primary key,
title varchar(100),
yearOfPrinting int
);

create table authorToBook(
idAuthor int,
idBook int
);

alter table authorToBook add constraint foreign key(idAuthor) references authors(id);
alter table authorToBook add constraint foreign key(idBook) references books(id);

