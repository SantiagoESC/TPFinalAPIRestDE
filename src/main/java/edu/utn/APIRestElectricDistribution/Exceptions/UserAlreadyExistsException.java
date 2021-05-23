package edu.utn.APIRestElectricDistribution.Exceptions;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(){}

    public UserAlreadyExistsException(String message){
        super(message);
    }

    public String getMessage(){
        return "User already exist!";
    }
}
