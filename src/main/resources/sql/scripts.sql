/*
    1. Generar las estructuras necesarias para dar soporte a 4 sistemas diferentes :
        a) BACKOFFICE, que permitirá el manejo de clientes, medidores y tarifas.
        b) CLIENTES, que permitirá consultas de mediciones y facturación.
        c) MEDIDORES,, que será el sistema que enviará la información de
        mediciones a la base de datos.
        d) FACTURACIÓN , proceso automático de facturación.
*/

-- ==========================================================
--                   USUARIOS
-- ==========================================================

/* BACKOFFICE */ 
create user 'backoffice'@'127.0.0.1' identified by 'b4ck0ffic3';
/*Permisos*/
grant select, insert ,update on electric_distribution.USERS to 'backoffice'@'127.0.0.1';
grant select, insert ,update on electric_distribution.ELECTRICAL_METERS to 'backoffice'@'127.0.0.1';
grant select, insert ,update on electric_distribution.ADDRESSES to 'backoffice'@'127.0.0.1';
grant select, insert ,update on electric_distribution.RATES to 'backoffice'@'127.0.0.1';
grant select on electric_distribution.BILLS to 'backoffice'@'127.0.0.1';

/* CLIENTES : Sistema WEB - APP MOBILE */
create user 'web'@'127.0.0.1' identified by 'w3b' ; /*'mobile'@'10.0.0.%'*/
/*Permisos*/
grant select on electric_distribution.USERS to 'web'@'127.0.0.1';
grant select on electric_distribution.ELECTRICAL_MEASUREMENTS to 'web'@'127.0.0.1';
grant select on electric_distribution.BILLS to 'web'@'127.0.0.1';

/*MEDIDORES: Sistema Infraestructura*/
create user 'infra'@'127.0.0.1' identified by '1fr43s7ruc7ur4';
/*Permisos*/
/*tambien se puede implementar utilizando el DEFIINER en el PROCEDURE sp_insert_call*/
grant insert on electric_distribution.ELECTRICAL_MEASUREMENTS to 'infra'@'127.0.0.1';

/*
    2) La facturación se realizará por un proceso automático en la base de datos. Se
    debe programar este proceso para el primer día de cada mes y debe generar una
    factura por medidor y debe tomar en cuenta todas las mediciones no facturadas
    para cada uno de los medidores, sin tener en cuenta su fecha. La fecha de vencimiento de
    esta factura será estipulado a 15 días.
*/

DELIMITER $$
CREATE PROCEDURE sp_create_bill(pId_electrical_meter int)
    BEGIN

        DECLARE vCountBillsUnpaid INT;

        DECLARE vElectricalMeasurementInitialID INT;
        DECLARE vElectricalMeasurementFinalID INT;
        DECLARE vRateID INT;
        DECLARE vDateBill DATE;
        DECLARE vExpirationDate DATE;
        /*DECLARE vIsPaid BOOLEAN = false;*/
        DECLARE vTotalPrice DOUBLE;

        SELECT COUNT(*) INTO vBillsUnpaid FROM bills b WHERE b.ELECTRICAL_METER_ID = pId_electrical_meter AND b.ISPAID = 0;

        IF vCountBillsUnpaid > 0 THEN
            SELECT SUM(TOTAL_PRICE) INTO vTotalPrice FROM BILLS b WHERE b.IS_PAID = 0 AND b.ELECTICAL_METER_ID = pId_electrical_meter;
            SET vDateBill = now();
            SET vExpirationDate = DATE_ADD(NOW(), INTERVAL 15 DAY);

            INSERT INTO BILLS(ELECTRICAL_METER_ID, ELECTRICAL_MEASUREMENT_INITIAL_ID, ELECTRICAL_MEASUREMENT_FINAL_ID, RATE_ID,
                                DATE_BILL, EXPIRATION_DATE, IS_PAID, TOTAL_PRICE)
            VALUES(pId_electrical_meter, vElectricalMeasurementInitialID, vElectricalMeasurementFinalID, vRateID,
                   vDateBill, vExpirationDate, FALSE, vTotalPrice);

        END IF;
    END
$$

----------------------------------
/*Event for billing once a month*/

CREATE EVENT billAll
ON SCHEDULE EVERY 1 MINUTE STARTS NOW()
DO CALL sp_create_all_bill();

--Store procedure que crea todas las facturas
--drop procedure sp_create_all_bill;
DELIMITER $$
CREATE PROCEDURE sp_create_all_bill()
BEGIN
    declare vId_electrical_meter int;
    --Declaro el cursos para recorrer todos los electrical meters
    declare electrical_meters_cursor cursor for
    select ID from ELECTRICAL_METERS el;

    --#Declaración de un manejador de error tipo NOT FOUND
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET @done = TRUE;
    --Se abre el cursor. Al abrir el cursor este sitúa un puntero a la primera fila del resultado de la consulta.
    OPEN electrical_meters_cursor;
    --Empieza el bucle de lectura
    loop1: LOOP
    --Se guarda el resultado en la variable, hay una variable y un campo en el SELECT de la declaración del cursor
    FETCH electrical_meters_cursor INTO vId_electrical_meter;
    --Llamo al store procedure para crear la factura de esa electrical meter
    call sp_create_bill(vId_electrical_meter);

    --Se sale del bucle cuando no hay elementos por recorrer
    IF @done THEN
        LEAVE loop1;
END IF;
END LOOP loop1;
    --Se cierra el cursor
    CLOSE electrical_meters_cursor;
END $$;


-- =================================================================
--      TRIGGERS
-- =================================================================
/*Verifica que el dni contenga solo numeros, y que el nombre y apellido no contenga numeros.*/

--drop trigger TBI_user;
DELIMITER $$
CREATE TRIGGER TBI_user BEFORE INSERT ON users FOR EACH ROW
BEGIN
    if( !isNumeric(new.DOCUMENT_NUMBER))then
        signal sqlstate '10001'
        SET MESSAGE_TEXT = 'DOCUMENT_NUMBER INCORRECT FORMAT',
        MYSQL_ERRNO = 4;
end if;

if(conteinsNumbers(new.firstname)) then
        signal sqlstate '10001'
        SET MESSAGE_TEXT = 'FIRST_NAME INCORRECT FORMAT',
        MYSQL_ERRNO = 5;
end if;

    if(conteinsNumbers(new.lastname)) then
        signal sqlstate '10001'
        SET MESSAGE_TEXT = 'LAST_NAME INCORRECT FORMAT',
        MYSQL_ERRNO = 6;
end if;
END $$ ;

 ----TRIGGER SET BILL ID AFTER INSERT ON BILLS ----
DELIMITER $$
CREATE TRIGGER tai_setBillId AFTER INSERT ON BILLS FOR EACH ROW
BEGIN
    UPDATE BILLS SET ID = new.ID WHERE ELECTRICAL_METER_ID = new.ELECTRICAL_METER_ID;
END;

/* --------------------------------------------- */

--drop  procedure sp_insert_rate;
DELIMITER $$
CREATE PROCEDURE sp_insert_rate (pTypeRate VARCHAR(30), pPrice DOUBLE )
BEGIN
    START TRANSACTION;
    INSERT INTO RATES(TYPE_RATE, price) VALUES (pTypeRate, pPrice);
COMMIT;
END $$;

DELIMITER $$
--drop  procedure sp_update_rate;
CREATE PROCEDURE sp_update_rate (pTypeRate VARCHAR(30), pPrice DOUBLE )
    BEGIN
    START TRANSACTION;
        UPDATE RATES
        SET price = pPrice WHERE TYPE_RATE = pTypeRate;
    COMMIT;
END $$;

DELIMITER $$
--drop  procedure sp_delete_rate;
CREATE PROCEDURE sp_delete_rate(pTypeRate VARCHAR(30))
BEGIN
    START TRANSACTION;
    DELETE FROM RATES
    WHERE TYPE_RATE = pTypeRate;
COMMIT;
END $$


-- ==========================================================
--                   Vistas
-- ==========================================================

    CREATE VIEW v_bills
    AS
    SELECT 

        u.DOCUMENT_NUMBER as documentNumber,
        CONCAT(u.FIRST_NAME,", ",u.LAST_NAME) AS fullName,
        CONCAT(em.BRAND, ", ", em.MODEL, ", ", em.SERIAL_NUMBER) AS electricalMeter,
        CONCAT(a.STREET_NAME, " ", a.STREET_NUMBER, ", ", a.ZIP_CODE, ", ", a.CITY ) AS address,
        r.TYPE_RATE AS rateType,
        r.PRICE AS priceByRate,

        emi.MEASUREMENT_DATE AS measurementDateInitial,
        emi.MEASUREMENT_KWH AS measurementInitial,

        emf.MEASUREMENT_DATE AS measurementDateFinal,
        emf.MEASUREMENT_KWH AS measurementFinal,

        b.EXPIRATION_DATE AS expirationDate,

        (emf.MEASUREMENT_KWH - emi.MEASUREMENT_KWH)  AS measurementTotalKWH,
        (r.PRICE * (emf.MEASUREMENT_KWH - emi.MEASUREMENT_KWH)) AS totalAmount,

        b.IS_PAID AS isPaid

    FROM USERS u
    INNER JOIN ELECTRICAL_METERS em ON u.ID = em.ID_USER
    INNER JOIN ADDRESS a ON em.ADDRESS_ID = a.ID
    INNER JOIN BILLS b ON b.ELECTRICAL_METER_ID = em.ID
    INNER JOIN RATES r ON b.RATE_ID = r.ID
    INNER JOIN ELECTRICAL_MEASURMENTS emi ON b.ELECTRICAL_MEASUREMENT_INITIAL_ID = em.ID
    INNER JOIN ELECTRICAL_MEASURMENTS emf ON b.ELECTRICAL_MEASUREMENT_FINAL_ID = em.ID
    where  u.IS_ENABLED = true;


/*
4) Generar las estructuras necesarias para dar soporte a las consultas de mediciones
por fecha y por usuario , debido a que tenemos restricción de que estas no pueden demorar
más de dos segundos y tenemos previsto que tendremos 500.000.000 de mediciones en el
sistema en el mediano plazo. Este reporte incluirá :

        ● Cliente
        ● Medidor
        ● Fecha medición
        ● Medicion
        ● Consumo Kwh
        ● Consumo precio
*/

-- ==========================================================
--                   INDICES
-- ==========================================================

create index idx_electrical_measurement on ELECTRICAL_MEASURMENTS (MEASUREMENT_DATE) using btree; 
/*si tmb se filtra por usuario no hace falta ya que dni es un indice unq*/

explain SELECT 

    u.DOCUMENT_NUMBER as documentNumber,
    CONCAT(u.FIRST_NAME,", ",u.LAST_NAME) AS fullName,
    CONCAT(em.BRAND, ", ", em.MODEL, ", ", em.SERIAL_NUMBER) AS electricalMeter,
    CONCAT(a.STREET_NAME, " ", a.STREET_NUMBER, ", ", a.ZIP_CODE, ", ", a.CITY ) AS address,

    emi.MEASUREMENT_DATE AS measurementDateInitial,
    emi.MEASUREMENT_KWH AS measurementInitial,

    emf.MEASUREMENT_DATE AS measurementDateFinal,
    emf.MEASUREMENT_KWH AS measurementFinal,

    b.EXPIRATION_DATE AS expirationDate,

    (emf.MEASUREMENT_KWH - emi.MEASUREMENT_KWH)  AS measurementTotalKWH,
    (r.PRICE * (emf.MEASUREMENT_KWH - emi.MEASUREMENT_KWH)) AS totalAmount,

    FROM USERS u
    INNER JOIN ELECTRICAL_METERS em ON u.ID = em.ID_USER
    INNER JOIN ADDRESS a ON em.ADDRESS_ID = a.ID
    INNER JOIN BILLS b ON b.ELECTRICAL_METER_ID = em.ID
    INNER JOIN RATES r ON b.RATE_ID = r.ID
    INNER JOIN ELECTRICAL_MEASURMENTS emi ON b.ELECTRICAL_MEASUREMENT_INITIAL_ID = em.ID
    INNER JOIN ELECTRICAL_MEASURMENTS emf ON b.ELECTRICAL_MEASUREMENT_FINAL_ID = em.ID

AND MEASUREMENT_DATE BETWEEN measurementDateInitial AND measurementDateFinal ;