package com.shoppingbackend.user.UerAPI.Exception;

import com.shoppingbackend.user.UerAPI.DTO.GeneralUserDTO.UserDTO;

public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException(String message){
        super(message);
    }
}
