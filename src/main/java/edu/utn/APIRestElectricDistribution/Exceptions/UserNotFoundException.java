package edu.utn.APIRestElectricDistribution.Exceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(){}

    public UserNotFoundException(Exception e){
        super(e);
    }
}
