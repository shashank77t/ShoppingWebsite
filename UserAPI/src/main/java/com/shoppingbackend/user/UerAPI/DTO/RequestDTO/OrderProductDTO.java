package com.shoppingbackend.user.UerAPI.DTO.RequestDTO;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderProductDTO {
    UUID productID;
    int quantity;
}
