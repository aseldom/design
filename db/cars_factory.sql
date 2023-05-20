create table car_bodies(
	id serial primary key,
	name text
);

create table car_engines(
	id serial primary key,
	name text
);

create table car_transmissions(
	id serial primary key,
	name text
);

create table cars(
	id serial primary key,
	name text,
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies (name) values
	('Sedan'),
	('Hatchback'),
	('Convertible'),
	('SUV'),
	('Coupe');
	
insert into car_engines (name) values
	('Gasoline engine'),
	('Diesel engine'),
	('Electric motor'),
	('Hydrogen engine'),
	('Hybrid engine'),
	('Hybrid engine2');

insert into car_transmissions (name) values
	('Manual transmission'),
	('Automatic transmission'),
	('Automated manual transmission'),
	('Continuously variable transmission - CVT'),
	('Semi-automatic transmission');

insert into cars (name, body_id, engine_id, transmission_id) values
	('Toyota', 1, 1, 4),
	('Volkswagen', 1, 2, 2),
	('Ford', 2, 3, 4),
	('Chevrolet',1, 4, 3),
	('Honda', 4, 1, 3),
	('BMW', 2, 5, 5),
	('Mercedes-Benz', 1, 5, 5),
	('Audi', 4, 2, 2),
	('Nissan', 2, 2, 3),
	('Volvo', 2, 3, 4),
	('Tesla', 2, 3, null),
	('Hyundai', null, null, 2);
	
-- Вывести список всех машин и все привязанные к ним детали.
select c.id, c.name car_name, cb.name body_name, ce.name engine_name, ct.name transmission_name
from cars c
left join car_bodies cb on c.body_id = cb.id
left join car_engines ce on c.engine_id = ce.id
left join car_transmissions ct on c.transmission_id = ct.id;

-- Вывести кузова, которые не используются НИ в одной машине.
select cb.name body_name
from cars c 
right join car_bodies cb on c.body_id = cb.id
where c.name is null;

-- Вывести двигатели, которые не используются НИ в одной машине, аналогично п.2;
select ce.name engine_name
from cars c 
right join car_engines ce on c.engine_id = ce.id
where c.name is null;


-- Вывести коробки передач, которые не используются НИ в одной машине, аналогично п.2.
select ct.name transmission_name
from cars c 
right join car_transmissions ct on c.transmission_id = ct.id
where c.name is null;
