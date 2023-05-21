create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);
	
-- 1)  Триггер должен срабатывать после вставки данных, для любого товара и просто насчитывать налог на товар (нужно прибавить налог к цене товара). Действовать он должен не на каждый ряд, а на запрос (statement уровень)
create or replace function tax_after()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger_after
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax_after();

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
select * from products;


-- 2) Триггер должен срабатывать до вставки данных и насчитывать налог на товар (нужно прибавить налог к цене товара). Здесь используем row уровень
alter table products disable trigger tax_trigger_after;

create or replace function tax_before()
    returns trigger as
$$
    BEGIN
        new.price = new.price * 1.2;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create or replace trigger tax_trigger_before
    before insert on products
    for each row
    execute procedure tax_before();

insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 8, 50);
select * from products;

-- 3) Нужно написать триггер на row уровне, который сразу после вставки продукта в таблицу products, будет заносить имя, цену и текущую дату в таблицу history_of_price.
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

alter table products disable trigger tax_trigger_before;

create or replace function history()
	returns trigger as 
$$
	BEGIN
		insert into history_of_price(name, price, date)
		values(new.name, new.price, current_date);
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create or replace trigger history_trigger
	after insert on products
	for each row
	execute procedure history();
	
	
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 8, 50);
select * from history_of_price;