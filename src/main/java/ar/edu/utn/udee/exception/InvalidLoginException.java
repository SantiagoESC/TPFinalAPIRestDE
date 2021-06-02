package ar.edu.utn.udee.exception;

public class InvalidLoginException extends RuntimeException{

    public InvalidLoginException(Throwable throwable) {
        super(throwable);
    }

    public InvalidLoginException(String message) {
        super(message);
    }

}
