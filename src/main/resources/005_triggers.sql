-- =================================================================
--  TRIGGERS
-- =================================================================
use electric_distribution;
# Verificar que el ID Card number (DNI) contenga solo numeros y nombre/apellido solo letras

DELIMITER $$
CREATE TRIGGER TBI_user BEFORE INSERT  ON users FOR EACH  ROW
BEGIN

    IF(!isNumeric(new.ID_card_number))then
        signal sqlstate '10001'
        SET MESSAGE_TEXT = 'The ID Card number has an incorrect format',
        MYSQL_ERRNO = 4;
    END IF;

    IF(conteinsNumbers(new.first_name)) then
        signal sqlstate '10001'
		SET MESSAGE_TEXT = 'First Name has an incorrect format',
		MYSQL_ERRNO = 5;
    END IF;

    IF(conteinsNumbers(new.last_name)) then
            signal sqlstate '10001'
            SET MESSAGE_TEXT = 'Last name has an incorrect format',
            MYSQL_ERRNO = 5;
    END IF;
END $$;