DROP TBALE IF EXISTS Employee;

CREATE TABLE Employee(
user_id int auto_increment NOT NULL Primary Key,
first_name varchar(200),
last_name varchar(200),
email varchar(200),
age int
);
