package com.shoppingbackend.user.UerAPI.DTO;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SellerProductRegistrationDTO {


        String productName;
        int price;
        int quantity;
        UUID sellerID;
        String productType;


}


