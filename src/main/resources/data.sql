insert into users (username, password, email, first_name, last_name, user_role) values ('laza', 'password1', 'laza@l.com', 'Lazar','Lazic', 'ROLE_ADMIN');
insert into users (username, password, email, first_name, last_name, user_role) values ('mika', 'password2', 'm@m.com', 'Mihajlo','Mikic', 'ROLE_CLIENT');
insert into users (username, password, email, first_name, last_name, user_role) values ('zika', 'password3', 'zika@z.com', 'Zika','Zikic', 'ROLE_ADMIN');
insert into users (username, password, email, first_name, last_name, user_role) values ('dule', 'password4', 'du@le.com', 'Dule','Dukic', 'ROLE_CLIENT');

insert into logs (id, client, created_date, log_type, message) values (1, 'mika', CURRENT_DATE, '0', 'EMERGENCY - System is unusable');
insert into logs (id, client, created_date, log_type, message) values (2, 'dule', CURRENT_DATE, '6', 'INFORMATIONAL - 	Informational messages');
insert into logs (id, client, created_date, log_type, message) values (3,'dule', CURRENT_DATE, '0', 'EMERGENCY - System is unusable');
insert into logs (id, client, created_date, log_type, message) values (4, 'mika', CURRENT_DATE, '1', 'ALERT - Action must be taken immediately');
insert into logs (id, client, created_date, log_type, message) values (5, 'dule', CURRENT_DATE, '3', 'ERROR - Error conditions');