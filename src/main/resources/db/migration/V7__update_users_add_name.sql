alter table users add name varchar(100) not null DEFAULT '';
update users set name = 'NEED_TO_UPDATE';