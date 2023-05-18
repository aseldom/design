create table mother(
	id serial primary key,
	name varchar(255)
	
);

create table child(
	id serial primary key,
	name varchar(255),
	mother_id int references mother(id)
);

insert into mother (name) values ('Olga');
insert into child (name, mother_id) values ('Egor', 1);

select * from child;
select * from mother where id in (select mother_id from child);
