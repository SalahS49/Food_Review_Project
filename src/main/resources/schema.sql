drop table if exists restaurant CASCADE;
drop table if exists review CASCADE;

create table restaurant (
id integer not null auto_increment,
restaurant varchar(64) not null,
food_type varchar(64) not null,
food varchar(64) not null,
price double not null,
restaurant varchar(64) not null, primary key (id));

create table review (
id integer not null auto_increment,
taste integer not null check (rating>=1 AND rating<=10),
appearance integer not null check (rating>=1 AND rating<=10),
rating integer, primary key (id) ,
foreign key (restaurant_id) references restaurant(id)
);