create user 'logparser'@'localhost' identified by 'logparser'
grant usage on *.* to logparser@localhost identified by 'logparser'

create database logparser_db
grant all privileges on logparser_db.* to logparser@localhost identified by 'logparser'