create database hotelmanagementsystem;
use hotelmanagementsystem;
create table login (username varchar(25), password varchar(25));
insert into login values("admin", "1234");
select * from login;

create table employee
(
	name varchar(25),
    age varchar(10),
    gender varchar(15),
    job varchar(30), 
    salary varchar(15), 
    phone varchar(10),
    email varchar(40),
    id varchar(12)    
);

describe employee;

select * from employee;

create table room
(
	room_number varchar(10),
    availability varchar(20),
    cleaning_status varchar(20),
    price varchar(20), 
    bed_type varchar(20)
);

select * from room;

create table customer
(
	document varchar(20),
    number varchar(30),
    name varchar(30),
    gender varchar(15),
    country varchar(30),
    room varchar(10), 
    checkintime varchar(80),
    deposit varchar(20)
);
select * from customer;

create table department(
	department varchar(30),
    budget varchar(30)
);

insert into department values
	('Font Officce', '500000'),
	('Housekeeping', '40000'),
    ('Food Beverage', '230000'),
    ('Kitchen Or Food Production', '540000'),
    ('Security', '320000');
    
select * from department;
  
