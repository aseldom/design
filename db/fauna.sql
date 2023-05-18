create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);


insert into fauna (name, avg_age, discovery_date) values ('name1', 100, '1930-03-01');
insert into fauna (name, avg_age, discovery_date) values ('name2', 100, '1933-03-01');
insert into fauna (name, avg_age, discovery_date) values ('name3', 100, '1946-03-01');
insert into fauna (name, avg_age, discovery_date) values ('big fish', 100, '1999-03-01');
insert into fauna (name, avg_age, discovery_date) values ('name5', 19000, '2000-03-01');
insert into fauna (name, avg_age, discovery_date) values ('name6', 100, '1950-03-01');
insert into fauna (name, avg_age, discovery_date) values ('name7', 100, '1930-03-01');
insert into fauna (name, avg_age, discovery_date) values ('name8', 100, '1930-03-01');
insert into fauna (name, avg_age, discovery_date) values ('Ameerega fishmuya', 100, '1930-03-01');
insert into fauna (name, avg_age, discovery_date) values ('name10', 100, '1930-03-01');
insert into fauna (name, avg_age, discovery_date) values ('Eryx miliaris', 11000, '1930-03-01');
insert into fauna (name, avg_age, discovery_date) values ('name12', 100, '1930-03-01');
insert into fauna (name, avg_age, discovery_date) values ('name13', 100, '1930-03-01');
insert into fauna (name, avg_age, discovery_date) values ('name14', 100, '1930-03-01');
insert into fauna (name, avg_age, discovery_date) values ('name15', 11000, '1990-03-01');
insert into fauna (name, avg_age, discovery_date) values ('name16', 100, '1900-03-01');
insert into fauna (name, avg_age, discovery_date) values ('name17', 100, '1930-03-01');
insert into fauna (name, avg_age, discovery_date) values ('name18', 100, '1930-03-01');
insert into fauna (name, avg_age, discovery_date) values ('name19', 100, '1980-03-01');
insert into fauna (name, avg_age, discovery_date) values ('name20', 100, '1930-03-01');
insert into fauna (name, avg_age, discovery_date) values ('name21', 1010, null);
insert into fauna (name, avg_age, discovery_date) values ('name22', 100, null);


select * from fauna where name like '%fish%'
select * from fauna where avg_age >= 10000 and avg_age <= 21000
select * from fauna where discovery_date is null
select * from fauna where discovery_date < '1950-01-01'