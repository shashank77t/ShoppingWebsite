package com.shoppingbackend.user.UerAPI.Exception;

public class UnAuthorized extends RuntimeException {
   public  UnAuthorized(String message){
       super(message);
   }
}
