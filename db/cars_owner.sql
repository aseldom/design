create table people (
	id serial primary key,
	name text
);

create table cars (
    id serial primary key,
    name text,
    color text,
    date date,
	people_id int references people(id)
);

insert into people (name) values
	('name1'),
	('name2'),
	('name3'),
	('name4'),
	('name5'),
	('name6');

insert into cars (name, color, date, people_id) values
	('car1', 'blue', '1999-03-01', 1),
	('car2', 'red', '1995-03-01', 3),
	('car3', 'black', '1993-03-01', 3),
	('car4', 'pink', '1992-03-01', 1),
	('car5', 'white', '1991-03-01', 5),
	('car6', 'orange', '1994-03-01', 2),
	('car7', 'gray', '1981-03-01', 5),
	('car8', 'gold', '1984-03-01', 1);

select p.name as Владелец , c.name as Марка, c.color as Цвет, c.date as "Дата выпуска" from cars as c join people as p on c.people_id = p.id order by p.name;
select p.name as Владелец , c.name as Марка, c.color as Цвет, c.date as "Дата выпуска" from cars as c join people as p on c.people_id = p.id where c.date > '1994.05.05' order by p.name;
select p.name as Владелец , c.name as Марка, c.color as Цвет, c.date as "Дата выпуска" from cars as c join people as p on c.people_id = p.id where c.date > '1994.05.05' order by p.name limit 1;