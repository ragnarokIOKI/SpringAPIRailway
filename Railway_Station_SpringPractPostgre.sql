create table train_type
(
	id_train_type serial primary key,
	name_type varchar (50) not null unique
);

create table route_status
(
	id_route_status serial primary key,
	name_route_status varchar (50) not null unique
);

create table "role"
(
	id_role serial primary key,
	name_role varchar (50) not null unique
);

create table users
(
	id_user serial primary key,
	first_name varchar (30) not null,
	second_name varchar (30) not null,
	middle_name varchar (30) default ('-'),
	birthday date not null,
	passport_series varchar (5) not null check (length(passport_series) = 5),
	passport_number varchar (6) not null check (length(passport_number) = 6),
	card_number varchar (19) not null unique check (length(card_number) = 19),
	card_holder varchar (100) not null,
	validity varchar (5) not null check (validity ~ '^\d{2}/\d{2}$'),
	username varchar (32) not null unique,
	password varchar (255) not null,
	role_id integer not null references role (id_role)
);
ALTER TABLE IF EXISTS public.users
    ADD COLUMN active boolean NOT NULL DEFAULT True;
	
create table train
(
	id_train serial primary key,
	number_wagons integer not null check (number_wagons >= 1),
	number_train varchar (4) not null unique check (length(number_train) <= 4),
	train_type_id integer not null references train_type (id_train_type)
);

create table route
(
	id_route serial primary key,
	number_route varchar (4) not null unique check (length(number_route) = 4),
	departure_time_route time not null default current_time,
	arrival_time_route time not null,
	departure_point varchar (30) not null,
	arrival_point varchar (30) not null,
	route_status_id integer not null references route_status (id_route_status),
	check (arrival_time_route > departure_time_route)
);

create table route_composition
(
	id_route_composition serial primary key,
	train_id integer not null references train (id_train),
	route_id integer not null references route (id_route)
);

create table seat_type
(
	id_seat_type serial primary key,
	name_seat_type varchar (10) not null unique
);

create table ticket
(
	number_ticket varchar (8) not null unique,
	number_seat integer not null,
	seat_type_id integer not null references seat_type (id_seat_type),
	user_id integer not null references users (id_user),
	route_id integer not null references route (id_route),
	primary key (number_ticket),
	check (length(number_ticket) = 8)
);

--Type Train
insert into train_type (name_type)
values ('Старого поколения'),
('Нового поколения');

--Role
insert into "role" (name_role)
values ('ADMIN'),
('PASSENGER'),
('EMPLOYEE');

--User
insert into users (first_name, second_name, middle_name, birthday, passport_series, passport_number, card_number, card_holder, validity, username, password, role_id)
values ('Брант', 'Кирилл', 'Григорьевич', '1991-03-18', '41 51', '879876',  '4414 3678 6507 9613', 'Brant Kirill',  '10/23',  'ivanov1979', 'R9LwT@', 2),
('Январёва', 'Варвара', 'Герасимовна', '1976-07-23', '45 37', '450466','5199 9841 2796 8741', 'Janvareva Varvara', '08/22', 'Janueary666', 'dGkx*B', 1),
('Ямова', 'Раиса', 'Леонтьева', '1976-01-01', '44 68', '343735','5538 2833 7041 4324', 'Yamova Raisa', '02/24', 'Nepheda81', '*c*p2N', 2),
('Халимдарова', 'Константина', 'Семёновна', '1986-04-21', '49 30', '485666','5994 5801 1081 4544', 'Halimdarova Konstantina', '04/25', 'Halima21', '%hXIPP', 3);

--Route Status
insert into route_status (name_route_status)
values ('По расписанию'),
('Отменён');

--Train
insert into train (number_train, number_wagons, train_type_id)
values ('413','15', 2),
('7201','20', 1),
('921','16', 1),
('732','19', 2),
('653','17', 1);

--Route
insert into "route" (number_route, departure_time_route, arrival_time_route, departure_point, arrival_point, route_status_id)
values ('Й137', '14:00:00', '16:00:00','Киевский вокзал' ,'Наро-Фоминск', 1),
('О005', '15:00:00', '18:00:00','Киевский вокзал' ,'Бекасово-1', 1),
('М133', '17:00:00', '20:00:00', 'Казанский вокзал' ,'Рассудово', 1),
('О858', '18:00:00', '21:00:00', 'Белгородский вокзал' ,'Тула', 1),
('О479', '19:00:00', '23:00:00', 'Ленинградский вокзал' ,'Орлово', 1);

--Seat Type
insert into seat_type (name_seat_type)
values ('Плацкарт'),
('Купе');

--Ticket
insert into ticket (number_ticket, number_seat, seat_type_id, route_id, user_id)
values ('20210987', '20', 1, 5, 1),
('20211357', '53', 2, 2, 3),
('20212468', '13', 1, 3, 1);

--Composition
insert into route_composition (train_id, route_id)
values (1,1),
(2,2),
(1,3);

