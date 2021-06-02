-- ==========================================================
--  Trabajo Practico - UDEE
-- ==========================================================
/*
create
database electric_distribution;
use
electric_distribution;
set
global time_zone = '-3:00';*/

//DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS
(
    ID         BIGINT auto_increment,
    FIRST_NAME VARCHAR(50) NOT NULL,
    LAST_NAME  VARCHAR(50) NOT NULL,
    ID_NUMBER  VARCHAR(10) NOT NULL,
    PASSWORD   VARCHAR(50) NOT NULL,
    IS_ENABLED    boolean default true,
    USER_ROLE  enum ('CLIENT', 'EMPLOYEE') NOT NULL,
    CONSTRAINT PK_USER PRIMARY KEY (ID),
    CONSTRAINT UNQ_ID_NUMBER UNIQUE (ID_NUMBER)
);

//DROP TABLE IF EXISTS ADDRESS;
CREATE TABLE ADDRESS
(
    ID         BIGINT auto_increment,
    STREET_NAME   VARCHAR(50) NOT NULL,
    STREET_NUMBER INT NOT NULL,
    FLOOR         INT,
    DEPARTAMENT    VARCHAR(5) ,
    ZIP_CODE      VARCHAR(10) NOT NULL,
    CITY          VARCHAR(30) NOT NULL,
    CONSTRAINT PK_ADDRESS PRIMARY KEY (ID)
);

//DROP TABLE IF EXISTS ELECTRICAL_METERS;
CREATE TABLE ELECTRICAL_METERS
(
    ID         BIGINT auto_increment,
    USER_ID       BIGINT NOT NULL,
    ADDRESS_ID    BIGINT NOT NULL,
    SERIAL_NUMBER VARCHAR(30) NOT NULL,
    BRAND         VARCHAR(30) NOT NULL,
    MODEL         VARCHAR(30) NOT NULL,
    IS_ENABLED    boolean default true,
    CONSTRAINT PK_ELECTRICAL_METER PRIMARY KEY (ID),
    CONSTRAINT FK_USER_ID FOREIGN KEY (USER_ID) references USERS (ID),
    CONSTRAINT FK_ADDRESS_ID FOREIGN KEY (ADDRESS_ID) references ADDRESS (ID),
    CONSTRAINT UNQ_SERIAL_NUMBER UNIQUE (SERIAL_NUMBER)
);

//DROP TABLE IF EXISTS ELECTRICAL_MEASURMENTS;
CREATE TABLE ELECTRICAL_MEASURMENTS
(
    ID         BIGINT auto_increment,
    ELECTRICAL_METER_ID BIGINT  NOT NULL,
    MEASUREMENT_DATE    DATE NOT NULL,
    MEASUREMENT_KWH     FLOAT NOT NULL,
    CONSTRAINT PK_ELECTRICAL_MEASUREMENT PRIMARY KEY (ID),
    CONSTRAINT fk_electrical_measurements FOREIGN KEY (ELECTRICAL_METER_ID) references ELECTRICAL_METERS (ID)
);

//DROP TABLE IF EXISTS RATES;
CREATE TABLE RATES
(
    ID         BIGINT auto_increment,
    TYPE_RATE varchar(30) not null,
    PRICE      DOUBLE NOT NULL,
    CONSTRAINT pk_rates PRIMARY KEY (id)
);

//DROP TABLE IF EXISTS BILLS;
CREATE TABLE BILLS
(
    ID         BIGINT auto_increment,
    ELECTRICAL_METER_ID               INT  NOT NULL,
    ELECTRICAL_MEASUREMENT_INITIAL_ID INT  NOT NULL,
    ELECTRICAL_MEASUREMENT_FINAL_ID   INT  NOT NULL,
    RATE_ID                           INT  NOT NULL,
    DATE_BILL                         DATE  NOT NULL,
    EXPIRATION_DATE                   DATE  NOT NULL,
    IS_PAID                           boolean default false,
    TOTAL_PRICE                       DOUBLE NOT NULL,
    CONSTRAINT PK_BILLS PRIMARY KEY (ID),
    CONSTRAINT fk_id_electrical_meter FOREIGN KEY (electrical_meter_id) REFERENCES ELECTRICAL_METERS (ID),
    CONSTRAINT fk_id_electrical_measurement_initial FOREIGN KEY (electrical_measurement_initial_id) REFERENCES ELECTRICAL_MEASURMENTS (ID),
    CONSTRAINT fk_id_electrical_measurement_final FOREIGN KEY (electrical_measurement_final_id) REFERENCES ELECTRICAL_MEASURMENTS (ID),
    CONSTRAINT fk_id_rate FOREIGN KEY (rate_id) REFERENCES RATES (ID)
);