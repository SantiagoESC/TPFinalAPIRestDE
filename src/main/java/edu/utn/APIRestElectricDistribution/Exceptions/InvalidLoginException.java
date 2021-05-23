package edu.utn.APIRestElectricDistribution.Exceptions;

public class InvalidLoginException extends Throwable{

    public InvalidLoginException(Throwable cause){
        super(cause);
    }

    public String getMessage(){
        return "Dni and/or password are incorrect";
    }
}
