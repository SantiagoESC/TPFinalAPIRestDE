create database electric_distribution;
use electric_distribution;
set global time_zone = '-3:00';


 ----------------------------------------------TABLES -------------------------------------------------------
create table users(

    id_user bigint auto_increment,
    first_name varchar(40) not null,
    last_name varchar(40) not null,
    ID_card_number varchar(20) not null ,
    password varchar(30) not null,
    enabled tinyint not null default 1,
    user_role enum("CLIENT", "EMPLOYEE") not null,
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

-------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------- INSERT DATA ---------------------------------------------------
/*   ---------   USERS --------------- --------------------------------------------------*/
insert into users(first_name, last_name, ID_card_number, password, enabled, user_role)
            values ('Franco', 'Acebo', '123', '123', 1, 'CLIENT'),
                   ('Santiago', 'Escribas', '456', '456', 1, 'EMPLOYEE');
/*---------------------------------------------------------------------------------------*/

/*----------  ADDRESS ---------------------------------------------------------------------*/
insert into address(street_name, street_number, zip_code, city)
            values('Paso', '1111', '7600', 'Mar del Plata'),
                  ('Necochea', '3333', '7600', 'Mar del Plata');

insert into address(street_name, street_number, floor, department, zip_code, city)
            values ('Buenos Aires', '222', 3, 'D', '7600', 'Mar del Plata'),
                   ('Calle 11', '209', 1, 'A', '7601', 'Batan');
/* ------------------------------------------------------------------------------------------*/

/*----------  ELECTRICAL METER ---------------------------------------------------------------------*/

insert into electrical_meter(id_user, id_address, serial_number, brand, model)
                    values (1, 1, 'A445DGFKN', 'Lux', 'AP-881E'),
                            (1,2, 'AR3456GD', 'Emylo', '60-A');
/* ------------------------------------------------------------------------------------------*/
/*----------  ELECTRICAL MEASUREMENTS ---------------------------------------------------------------------*/
insert into electrical_measurements(id_electrical_meter, measurement_date, measurement_kwh)
                        values (1, '2021-01-15', 150),
                               (2, '2021-02-15', 180);
/* ------------------------------------------------------------------------------------------*/
/*----------  RATES ---------------------------------------------------------------------*/
insert into rates(type_rates, price)
            values (),
                   ();
/* ------------------------------------------------------------------------------------------*/
/*----------  BILLS ---------------------------------------------------------------------*/
insert into bills(id_electrical_meter, electrical_measurement_initial_id, electrical_measurement_final_id, id_rate,
                    date_bill, expiration_date, billed, total_price)
            values (),
                ();
/* ------------------------------------------------------------------------------------------*/


-- =================================================================
--                          FUNCTIONS
-- =================================================================

/*Verifica que el string solo contenga enteros*/
CREATE FUNCTION isNumeric (word varchar(1024))
    RETURNS tinyint deterministic
    RETURN word REGEXP '^-?[0-9]+$';

/*Verifica que el string no contenga enteros*/
CREATE FUNCTION conteinsNumbers ( word varchar(1024))
    RETURNS tinyint deterministic
    RETURN word REGEXP '-?[0-9]';

-- ===============================================================
--                      PROCEDURES
-- ===============================================================

/*UPDATE_USER*/
DELIMITER $$
CREATE PROCEDURE sp_update_user(pFirstName varchar(40), pLastName varchar (40), pID_card_number varchar(20), out pIdUser bigint)
BEGIN
    START TRANSACTION;
        UPDATE users
        SET first_name = pFirstName,
            last_name = pLastName
        WHERE ID_card_number = pID_card_number;
        SELECT id_user INTO pIdUser FROM users WHERE ID_card_number = pId_card_number;
    COMMIT;
END $$;

-- -----------------------------------------------------------------

/* DELETE */

DELIMITER $$
CREATE PROCEDURE sp_delete_user(pID_card_number varchar(20))
BEGIN
    DECLARE vIdUser BIGINT;
    START TRANSACTION;
        UPDATE users
        SET users.enabled = 0
        WHERE users.ID_card_number = pID_card_number;

        SELECT u.id_user INTO vIdUser FROM users u WHERE u.ID_card_number = pID_card_number;


        /* ACA VA EL UPDATE DE ELECTRICAL METER

         */

    COMMIT;
END $$;

/* BILL */
DELIMITER $$
CREATE PROCEDURE sp_create_bill(pId_electrical_meter int)
BEGIN
    /* DECLARACIONES */


    /*
        LOGICA
     */

END $$;






-- =================================================================
--  TRIGGERS
-- =================================================================

/* Verificar que el ID Card number (DNI) contenga solo numeros y nombre/apellido solo letras */

DELIMITER $$
CREATE TRIGGER TBI_user BEFORE INSERT  ON users FOR EACH  ROW
BEGIN

    IF(!isNumeric(new.ID_card_number))then
        signal sqlstate '10001'
        SET MESSAGE_TEXT = 'The ID Card number has an incorrect format'
        MYSQL_ERRNO = 4;
    END IF;

    IF(conteinsNumbers(new.firstName)) then
        signal sqlstate '10001'
		SET MESSAGE_TEXT = 'First Name has an incorrect format',
		MYSQL_ERRNO = 5;
    END IF;

     IF(conteinsNumbers(new.lastName)) then
            signal sqlstate '10001'
            SET MESSAGE_TEXT = 'Last name has an incorrect format',
            MYSQL_ERRNO = 5;
    END IF;
END $$;