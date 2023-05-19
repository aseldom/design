create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into people (name) values ('Ivan Ivanov'), ('Roman Petrov'), ('Igor Svetlov');
insert into devices (name, price) values ('Iphone 10', '75000'), ('Ipad', '93000'), ('Computer Asus Rock', '56000'), ('watch', 4000);
insert into devices_people (people_id, device_id) values (1, 1), (1, 2), (3, 4), (2, 2), (2, 3), (2, 4), (3, 1), (3, 4); 


select avg(d.price) from devices d;

select p.name, sum(d.price)
from devices_people dp
join devices d on dp.device_id = d.id
join people p on dp.people_id = p.id
group by p.name;

select p.name, avg(d.price)
from devices_people dp
join devices d on dp.device_id = d.id
join people p on dp.people_id = p.id
group by p.name
having avg(d.price) > 5000;