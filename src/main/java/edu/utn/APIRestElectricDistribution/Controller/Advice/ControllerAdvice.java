package edu.utn.APIRestElectricDistribution.Controller.Advice;

import edu.utn.APIRestElectricDistribution.Dto.ErrorResponseDTO;
import edu.utn.APIRestElectricDistribution.Exceptions.InvalidLoginException;
import edu.utn.APIRestElectricDistribution.Exceptions.UserAlreadyExistsException;
import edu.utn.APIRestElectricDistribution.Exceptions.UserNotFoundException;
import edu.utn.APIRestElectricDistribution.Exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.ParseException;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidLoginException.class)
    public ErrorResponseDTO handleLoginException(InvalidLoginException invalidLoginException){
        return new ErrorResponseDTO(1, invalidLoginException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorResponseDTO handleValidationException(ValidationException validationException) {
        return new ErrorResponseDTO(2, validationException.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponseDTO handleUserNotFoundException() {
        return new ErrorResponseDTO(3, "User not exists");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ParseException.class)
    public ErrorResponseDTO handleParseException() {
        return new ErrorResponseDTO(4, "Not valid dates");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ErrorResponseDTO handleUserAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException) {
        return new ErrorResponseDTO(5, userAlreadyExistsException.getMessage());
    }
}
