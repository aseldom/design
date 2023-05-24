create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price) values ('name1', 'prod1', 12, 20);
insert into products (name, producer, count, price) values ('name2', 'prod2', 12, 20);
insert into products (name, producer, count, price) values ('name3', 'prod3', 12, 20);


-- transaction 1															transaction 2
begin isolation level serializable;											begin isolation level serializable;
select * from products;
update products set name = 'name2' where name = 'name1';					update products set name = 'name3' where name = 'name1';
commit;																		ОШИБКА:  не удалось сериализовать доступ из-за параллельного изменения postgres=!#
