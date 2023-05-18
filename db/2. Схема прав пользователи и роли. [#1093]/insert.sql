insert into role (name) values ('role_A');
insert into role (name) values ('role_B');
insert into role (name) values ('role_C');

insert into users (name, role_id) values ('User_1', 1);
insert into users (name, role_id) values ('User_2', 1);
insert into users (name, role_id) values ('User_3', 2);

insert into rules (name) values ('rule A');
insert into rules (name) values ('rule B');
insert into rules (name) values ('rule C');

insert into role_rules (role_id, rules_id) values (1, 2);
insert into role_rules (role_id, rules_id) values (1, 3);
insert into role_rules (role_id, rules_id) values (3, 2);
insert into role_rules (role_id, rules_id) values (2, 3);
insert into role_rules (role_id, rules_id) values (3, 3);

insert into category (name) values ('category_A');
insert into category (name) values ('category_B');
insert into category (name) values ('category_C');

insert into state (name) values ('state_A');
insert into state (name) values ('state_B');
insert into state (name) values ('state_C');

insert into item (name, users_id, category_id, state_id) values ('item_1', 2, 2, 2);
insert into item (name, users_id, category_id, state_id) values ('item_2', 1, 2, 3);
insert into item (name, users_id, category_id, state_id) values ('item_3', 3, 2, 1);
insert into item (name, users_id, category_id, state_id) values ('item_4', 1, 1, 1);

insert into comments (name, item_id) values ('comment_1', 1);
insert into comments (name, item_id) values ('comment_2', 1);
insert into comments (name, item_id) values ('comment_3', 1);
insert into comments (name, item_id) values ('comment_4', 2);

insert into attachs (name, path, item_id) values ('attach_1', 'path_1', 1);
insert into attachs (name, path, item_id) values ('attach_2', 'path_2', 1);
insert into attachs (name, path, item_id) values ('attach_3', 'path_3', 3);
insert into attachs (name, path, item_id) values ('attach_4', 'path_4', 3);