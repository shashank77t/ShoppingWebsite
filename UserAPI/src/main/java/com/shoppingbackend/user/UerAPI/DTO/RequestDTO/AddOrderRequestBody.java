package com.shoppingbackend.user.UerAPI.DTO.RequestDTO;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddOrderRequestBody {
    UUID userID;
    int quantity;
    int totalPrice;
}
