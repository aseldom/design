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

start transaction isolation level read committed;
update products set name = 'name45' where name = 'name3';
savepoint save_one;
update products set name = 'name99' where name = 'name1';
delete from products where name = 'name2';
savepoint save_two;
drop table products;
release savepoint save_two;
rollback to savepoint save_one;
commit;
select * from products;