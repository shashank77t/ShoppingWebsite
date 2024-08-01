package com.shoppingbackend.user.UerAPI.Exception;

public class MailNotSendException extends RuntimeException{
    public MailNotSendException(String message){
        super(message);
    }
}
