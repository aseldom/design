create table employe(
	id serial primary key,
	name varchar(255)
);

create table workstation(
	id serial primary key,
	name varchar(255)
);

create table employe_workstation(
	id serial primary key,
	emloye_id int references employe(id) unique,
	workstation_id int references workstation(id) unique
);