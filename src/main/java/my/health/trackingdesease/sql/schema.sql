

drop table deseases;
drop table deseases_users;
drop table user_profile;


create table persons(
	id int primary key auto_increment,
    firstname varchar(200) not null,
    lastname varchar(200) not null
) engine = innodb;
alter table persons drop column parent_id;
alter table persons add column mother_id int;
alter table persons add column father_id int;

alter table persons add constraint fk_parent_mother foreign key(mother_id) references persons(id);
alter table persons add constraint fk_parent_father foreign key(father_id) references persons(id);
 -- alter table persons add column user_id int;
alter table persons add constraint fk_user foreign key(user_id) references users(id);
create table deseases(
	id int primary key auto_increment,
    name varchar(500) not null
)engine = innodb;

create table deseases_persons(
	id_person int,
    id_desease int,
    primary key(id_person, id_desease),
    foreign key(id_person) references persons(id),
    foreign key(id_desease) references deseases(id)
)engine = innodb;

create table person_profile(
	id int primary key,
    height double,
    weight double,
    dob date,
    gender enum ('male', 'female'),  
    foreign key(id) references persons(id)
)engine = innodb;

alter table users drop column gender;

drop table desease_type;
drop table deseases_and_types;

create table desease_type(
	id int primary key auto_increment,
    typename varchar(300) not null unique
) engine = innodb;

create table deseases_and_types(
	desease_id int,
    type_id int,
    primary key(desease_id, type_id),
    foreign key(desease_id) references deseases(id),
    foreign key(type_id) references desease_type(id)
) engine = innodb;
