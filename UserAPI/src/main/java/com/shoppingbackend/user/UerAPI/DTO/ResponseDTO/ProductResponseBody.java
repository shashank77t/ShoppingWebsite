package com.shoppingbackend.user.UerAPI.DTO.ResponseDTO;

import com.shoppingbackend.user.UerAPI.DTO.GeneralUserDTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseBody {
    UUID id;
    String productName;
    int price;
    int quantity;

    UserDTO seller;
    double rating;
    String productType;
}
