-- =================================================================
--                          FUNCTIONS
-- =================================================================

/*Verifica que el string solo contenga enteros*/
CREATE FUNCTION isNumeric (word varchar(1024))
    RETURNS tinyint
    RETURN word REGEXP '^-?[0-9]+$';

/*Verifica que el string no contenga enteros*/
CREATE FUNCTION containsNumbers (word varchar(1024))
    RETURNS tinyint
    RETURN word REGEXP '-?[0-9]';