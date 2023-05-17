create table child(
	id serial primary key,
	name varchar(255)
);

create table mother(
	id serial primary key,
	name varchar(255),
	child_id int references child(id)
);

insert into child (name) values('Egor');
insert into mother (name, child_id) values ('Olga', 1);


select * from mother;
select * from child where id in (select child_id from mother);


