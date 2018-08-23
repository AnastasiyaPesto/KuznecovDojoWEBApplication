drop database if exists kuznecov_dojo;

create database if not exists kuznecov_dojo;
use kuznecov_dojo;

-- I N S T R U C T O R
create table if not exists instructor (
	instr_id int primary key auto_increment,
    first_name varchar(30) not null,
    second_name varchar(50),
    age int,
    phone varchar(15)
);

-- C E R T I F I C A T E
create table if not exists certificate (
	certif_id int primary key auto_increment,
    number varchar(10) not null unique,
    degree varchar(7) not null,
    date_pass date,
	instr_id int not null,
    foreign key (instr_id) references instructor(instr_id)
);

-- S P O R T   C L U B
create table if not exists sport_club (
	sc_id int primary key auto_increment,
    metro varchar(40),
    address varchar(200) not null,
    phone varchar(15)
);

-- Many-to-many instructor_sport_club
create table if not exists instructor_sport_club (
    instr_id int,
    sc_id int,
    constraint primary key(instr_id, sc_id),
    foreign key (instr_id) references instructor(instr_id),
    foreign key (sc_id) references sport_club(sc_id)
);