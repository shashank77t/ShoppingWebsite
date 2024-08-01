package com.shoppingbackend.user.UerAPI.Exception;

public class UnavailableProduct extends RuntimeException{
    public UnavailableProduct(String message){
        super(message);
    }
}
