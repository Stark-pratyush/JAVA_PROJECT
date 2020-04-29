create database schoolapp;
use schoolapp;
create table student(adno integer primary key, sname varchar(20),sadd varchar(40),sage integer, class integer,sbg varchar(2));
create table teacher(tid integer primary key, tname varchar(20),tsalary double,tdjoin varchar(20),tpost varchar(10));
create table books(bid integer primary key, bname varchar(20), bauthor varchar(20), bprice double);
create table sissued(sbid integer references books.bid, ssid integer references student.adno, bname varchar(20), bauthor varchar(20),doi varchar(20),entry int(10) primary key);
create table tissued(tbid integer references books.bid, ttid integer references teacher.tid, bname varchar(20), bauthor varchar(20),doi varchar(20),entry int(10) primary key);
