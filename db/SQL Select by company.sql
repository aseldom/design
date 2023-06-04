-- 1. В одном запросе получить
-- имена всех person, которые не состоят в компании с id = 5;
-- название компании для каждого человека.

select p.name, c.name from person p join company c on p.company_id = c.id where company_id != 5;


-- 2. Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании.
-- Нужно учесть, что таких компаний может быть несколько.

select c.name, count(*) from  company c
join person p on p.company_id = c.id
group by c.name
having count(*) = (select max(num) from (select(count(person.id)) as num from person group by person.company_id) as y)
