create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;

call insert_data('name1', 'prod1', 1, 9);
call insert_data('name2', 'prod2', 5, 130);
call insert_data('name3', 'prod3', 25, 250);

1. Функция, удаляет по id.
create or replace procedure delete_on_id (id_d integer) as
$$
	begin		
		delete from products where id = id_d;
	end;
$$ language 'plpgsql';

call delete_on_id(49);
select * from products

2. Процедура удаляет товары с нулевой стоимостью и выводит количество удаленных товаров.

call insert_data('name4', 'prod2', 5, 0);
call insert_data('name5', 'prod3', 25, 0);

create or replace function delete_price_0()
returns integer as
$$
	declare res integer;
	begin
		select into res count (price) from products where price = 0;
		delete from products where price = 0;
		return res;
	end;
$$ language 'plpgsql';

select delete_price_0();