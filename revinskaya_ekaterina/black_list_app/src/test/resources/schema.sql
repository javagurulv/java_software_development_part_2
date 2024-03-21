create table if not exists persons
(
id bigint not null auto_increment,
first_name varchar(200) not null,
last_name varchar(200) not null,
personal_code varchar(200) not null,
primary key(id)
);
CREATE UNIQUE INDEX if not exists ix_person
ON persons (personal_code);
