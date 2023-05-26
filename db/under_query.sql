CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);


insert into customers (first_name, last_name, age, country) VALUES ('Alex', 'Zamyatin', 25, 'Russia');
insert into customers (first_name, last_name, age, country) VALUES ('Egor', 'Letov', 33, 'France');
insert into customers (first_name, last_name, age, country) VALUES ('Petr', 'Onuf', 39, 'Germany');
insert into customers (first_name, last_name, age, country) VALUES ('Onch', 'Grim', 20, 'Finland');


-- 1. Выполните запрос, который вернет список клиентов, возраст которых является минимальным.
select first_name, last_name, age from customers where age = (select min(age) from customers);


-- 2. Необходимо выполнить запрос, который вернет список пользователей, которые еще не выполнили ни одного заказа. Используйте подзапрос, для реализации Вам понадобится NOT IN.
CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders (amount, customer_id) VALUES (100, 1);
insert into orders (amount, customer_id) VALUES (150, 1);
insert into orders (amount, customer_id) VALUES (160, 2);
insert into orders (amount, customer_id) VALUES (170, 2);
insert into orders (amount, customer_id) VALUES (180, 2);
insert into orders (amount, customer_id) VALUES (500, 3);

select * from customers where customers.id in (select customer_id from orders);

