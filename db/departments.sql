create table departments(
	id serial primary key,
	name text
);

create table employees(
	id serial primary key,
	name text,
	departments_id int references departments(id)
);

insert into departments (name) values
	('Human Resources'),
	('Finance'),
	('Marketing'),
	('Sales'),
	('Quality Assurance'),
	('Production');
	
insert into employees(name, departments_id) values
	('John', 1),
	('Michael', 1),
	('David', 1),
	('James', 2),
	('William', 2),
	('Robert', 3),
	('Christopher', 3),
	('Matthew', 3),
	('Andrew', 3),
	('Joseph', 3),
	('Jennifer', 4),
	('Jessica', 4),
	('Monica', null);
	
-- 2. Выполнить запросы с left, right, full, cross соединениями
-- 2.1 Выполнить запросы с left join
select *
from departments d 
left join employees e on d.id = e.departments_id;

-- 2.2 Выполнить запросы с right join
select * 
from departments d 
right join employees e on d.id = e.departments_id;

-- 2.3 Выполнить запросы с full join
select * 
from departments d 
full join employees e on d.id = e.departments_id;

-- 2.3 Выполнить запросы с cross join
select * 
from departments d 
cross join employees e;

-- 3. Используя left join найти департаменты, у которых нет работников
select *
from departments d
left join employees e
on d.id = e.departments_id
where e.id is null;

-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный). 
select *
from departments d
left join employees e
on d.id = e.departments_id;

select d.id, d.name, e.id, e.name, e.departments_id
from employees e
right join departments d
on d.id = e.departments_id;

-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
create table teens(
	id serial primary key,
	name text,
	gender boolean
);

insert into teens(name, gender) values
	('Alex', true),
	('Joseph', true),
	('Christopher', true),
	('Monica', false),
	('Jessica', false),
	('Jennifer', false)

select t1.name, t2.name, t1.gender, t2.gender
from teens t1
cross join teens t2
where t1.gender != t2.gender;;
	
