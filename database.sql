-- -----------------------------------------------------
-- Database coolfriends
-- -----------------------------------------------------

-- ***********************************************************************
-- Create Database
-- ***********************************************************************
CREATE DATABASE IF NOT EXISTS coolfriends ;
USE coolfriends ;

-- -----------------------------------------------------
-- Drop Tables If Exists
-- -----------------------------------------------------
DROP TABLE IF EXISTS coolfriends.login_history;
DROP TABLE IF EXISTS coolfriends.likes;
DROP TABLE IF EXISTS coolfriends.comments;
DROP TABLE IF EXISTS coolfriends.users;

-- ***********************************************************************
-- Create Tables
-- ***********************************************************************
-- Table coolfriends.users
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS coolfriends.users (
  id INT(8) NOT NULL auto_increment,
  type NVARCHAR(10) NOT NULL DEFAULT "user",
  name NVARCHAR(50) NOT NULL ,
  password NVARCHAR(100) NOT NULL ,
  gender NVARCHAR(20) NOT NULL DEFAULT "UNKNOWN",
  dob    date NOT NULL DEFAULT "1999-12-01",        
  contact_no NVARCHAR(20) NULL ,
  email NVARCHAR(100) NULL ,
  country NVARCHAR(50) NULL,
  city NVARCHAR(50) NULL,
  postalcode NVARCHAR(50) NULL,
  PRIMARY KEY (id)  );
  
  -- -----------------------------------------------------
-- Table coolfriends.login_history
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS coolfriends.login_history (
  id INT(8) NOT NULL auto_increment,
  user_id INT(8) NOT NULL ,
  ip_address NVARCHAR(20) NOT NULL DEFAULT "0:0:0:0:0:0:0:1",
  event_type NVARCHAR(6) NOT NULL,
  event_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT fk_login_history_users1
    FOREIGN KEY (user_id)
    REFERENCES coolfriends.users (id));
    
      -- -----------------------------------------------------
-- Table coolfriends.likes
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS coolfriends.likes (
  id INT(8) NOT NULL auto_increment,
  like_name NVARCHAR(50) NOT NULL,
  liked_name NVARCHAR(50) NOT NULL,
  like_user_id INT(8) NOT NULL,
  liked_user_id INT(8) NOT NULL,
  read_status           boolean NOT NULL DEFAULT False,
  event_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT fk_likes_users1
    FOREIGN KEY (like_user_id) REFERENCES coolfriends.users (id),
	FOREIGN KEY (liked_user_id) REFERENCES coolfriends.users (id));

      -- -----------------------------------------------------
-- Table coolfriends.comments
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS coolfriends.comments (
  id INT(8) NOT NULL auto_increment,
  comment_name NVARCHAR(50) NOT NULL,
  commented_name NVARCHAR(50) NOT NULL,
  comment_user_id INT(8) NOT NULL,
  commented_user_id INT(8) NOT NULL,
  message        varchar(200) NOT NULL,
  read_status           boolean NOT NULL DEFAULT False,
  event_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT fk_comments_users1
    FOREIGN KEY (comment_user_id) REFERENCES coolfriends.users (id),
	FOREIGN KEY (commented_user_id) REFERENCES coolfriends.users (id));

insert into coolfriends.users (type,name,password,gender,dob,contact_no,email,country, city, postalcode) values ('admin','albertleng','Hahaha123','male','2000-02-02','91136239','aleng001@.ntu.edu.sg','Singapore','Woodlands', '11111');
insert into coolfriends.users (type,name,password,gender,dob,contact_no,email,country, city, postalcode) values ('user','Happy','Hahaha123','female','1998-03-11','54567353','hahaha@e.ntu.edu.sg', 'Brazil', 'Rio','12345');
insert into coolfriends.users (type,name,password,gender,dob,contact_no,email,country, city, postalcode) values ('user','Aleng','Hahaha123','male','1999-04-12','65746345','hehehe@e.ntu.edu.sg', 'Vanuatu', 'Queenstown','23456');
insert into coolfriends.users (type,name,password,gender,dob,contact_no,email,country, city, postalcode) values ('user','YaoYeng','Hahaha123','female','1990-11-11','54522353','hohoho@e.ntu.edu.sg', 'Malaysia', 'Kota','12311');
insert into coolfriends.users (type,name,password,gender,dob,contact_no,email,country, city, postalcode) values ('user','Mari','Hahaha123','male','1999-04-12','65746345','hihihi@e.ntu.edu.sg', 'Indonesia', 'Batam','24556');
