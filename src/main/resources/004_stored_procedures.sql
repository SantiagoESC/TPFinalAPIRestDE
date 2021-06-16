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

        UPDATE electrical_meter
        SET electrical_meter.enabled = 0
        WHERE electrical_meter.id_user = vIdUser;
    COMMIT;
END $$;

/* BILL
DELIMITER $$
CREATE PROCEDURE sp_create_bill(pId_electrical_meter int)
BEGIN
    /* DECLARACIONES */


    /*
        LOGICA
     */

END $$;