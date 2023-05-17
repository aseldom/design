create table man(
	id serial primary key,
	name varchar(255)
);

create table shop(
	id serial primary key,
	name varchar(255)
);

create table man_shop(
	id serial primary key,
	man_id int references shop(id),
	shop_id int references man(id)
);

insert into man(name) values('Roman');
insert into man(name) values('Egor');
insert into man(name) values('Vasily');

insert into shop(name) values('Pyatjorochka');
insert into shop(name) values('Universam');
insert into shop(name) values('U_doma');

insert into man_shop(man_id, shop_id) values(1, 2);
insert into man_shop(man_id, shop_id) values(1, 3);
insert into man_shop(man_id, shop_id) values(2, 1);
insert into man_shop(man_id, shop_id) values(2, 2);
insert into man_shop(man_id, shop_id) values(2, 3);
insert into man_shop(man_id, shop_id) values(3, 3);