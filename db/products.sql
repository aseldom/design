create table type(
	id serial primary key,
	name text
);

create table product(
	id serial primary key,
	name text,
	expired_date date,
	price float,
	type_id int references type(id)
);

insert into type(name) values
('СЫР'),
('МОЛОКО'),
('КОЛБАСА'),
('ХЛЕБ'),
('МОРОЖЕНОЕ');

insert into product (type_id, name, expired_date, price) values
(1, 'Сыр киевский', '01.01.2024', 900),
(1, 'Сыр голландский', '02.01.2024', 1200),
(1, 'Сыр пошахонский', '03.01.2024', 700),
(1, 'Сыр бука', '04.01.2024', 600),
(2, 'Домик в деревне', '04.01.2024', 100),
(2, 'Мишкино', '02.01.2024', 80),
(2, 'Простоквашино', '04.02.2024', 90),
(3, 'Краковская', '04.03.2024', 1900),
(3, 'Московская', '02.03.2024', 900),
(3, 'Столичная', '04.03.2024', 800),
(3, 'Восточная', '04.03.2021', 1900),
(4, 'Белый', '01.02.2024', 90),
(4, 'Черный', '01.02.2024', 80),
(5, 'Сливочное', '01.02.2024', 90),
(5, 'Пломбир столичный', '01.02.2024', 120),
(5, 'Эскимо', '01.02.2024', 90),
(5, '33 пингвина', '01.02.2024', 160);

-- 1. Написать запрос получение всех продуктов с типом "СЫР"
select * from product p
join type t on p.type_id = t.id
where t.name = 'СЫР'

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select * from product p
join type t on p.type_id = t.id
where p.name like '%Сыр%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых уже истек
select * from product p
join type t on p.type_id = t.id
where p.expired_date > now();

-- 4. Написать запрос, который выводит самый дорогой продукт. Запрос должен быть универсальный и находить все продукты с максимальной ценой.
select * from product p
join type t on p.type_id = t.id
where p.price = (select max(product.price) from product);

-- 5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select t.name, count(t.name) from product p
join type t on p.type_id = t.id
group by t.name;

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product p
join type t on p.type_id = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО';

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.name, count(t.name) from product p
join type t on p.type_id = t.id
group by t.name
having count(t.name) < 10;

-- 8. Вывести все продукты и их тип.
select p.name, t.name from product p
join type t on p.type_id = t.id;
