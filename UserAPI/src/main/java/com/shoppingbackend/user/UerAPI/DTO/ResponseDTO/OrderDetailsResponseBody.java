package com.shoppingbackend.user.UerAPI.DTO.ResponseDTO;

import com.shoppingbackend.user.UerAPI.DTO.GeneralUserDTO.UserDTO;

import java.util.UUID;

public class OrderDetailsResponseBody {



    UUID id;
    int quantity;
    double totalPrice;
    boolean isDelivered;
    UserDTO user;
}
