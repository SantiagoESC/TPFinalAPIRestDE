create database electric_distribution;
use electric_distribution;
set global time_zone = '-3:00';

create table users(

    id_user bigint auto_increment,
    first_name varchar(40),
    last_name varchar(40),
    ID_card_number varchar(20) not null ,
    password varchar(30) not null,
    enabled tinyint not null default 1,
    user_role enum("CLIENT", "EMPLOYEE"),
    constraint pk_id_user primary key(id_user),
    constraint unq_id_card_number unique (ID_card_number)
);

create table address(

    id_address bigint auto_increment,
    street_name varchar(40) not null,
    street_number int not null ,
    floor int,
    department varchar(10),
    zip_code varchar(30) not null ,
    city varchar(30) not null ,

    constraint pk_address primary key (id_address)
);

create table electrical_meter(

    id_electrical_meter int auto_increment,
    id_user int not null,
    id_address int not null,
    serial_number varchar(40) not null ,
    brand varchar(40) not null ,
    model varchar(40) not null,

    constraint pk_id_electrical_meter primary key (id_electrical_meter),
    constraint fk_id_user foreign key (id_user) references users(id_user),
    constraint fk_id_address foreign key (id_address) references address(id_address)
);

create table electrical_measurements(

    id_electrical_measurement int auto_increment,
    id_electrical_meter int not null,
    measurement_date date not null,
    measurement_kwh long not null,

    constraint pk_electrical_measurements primary key (id_electrical_measurement),
    constraint fk_electrical_measurements foreign key (id_electrical_meter) references electrical_meter(id_electrical_meter)
);

create table rates(

  id_rate int auto_increment,
  type_rates varchar(30) not null ,
  price long not null,

  constraint pk_rates primary key (id_rate)
);

create table bills(

  id_bill int auto_increment,
  id_electrical_meter int not null,
  electrical_measurement_initial_id int not null,
  electrical_measurement_final_id int not null,
  id_rate int not null,
  date_bill date not null,
  expiration_date date not null,
  billed boolean default false,
  total_price long not null,

  constraint pk_bills primary key (id_bill),
  constraint fk_id_electrical_meter foreign key (id_electrical_meter) references electrical_meter(id_electrical_meter),
  constraint fk_id_electrical_measurement_initial foreign key (electrical_measurement_initial_id) references electrical_measurements(id_electrical_measurements),
  constraint fk_id_electrical_measurement_initial foreign key (electrical_measurement_initial_id) references electrical_measurements(id_electrical_measurements),
  constraint fk_id_rate foreign key (id_rate) references rates(id_rate)
);