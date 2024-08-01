package com.shoppingbackend.user.UerAPI.DTO.RequestDTO;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
    UUID userID;
    List<OrderProductDTO> orderProductDTOList=new ArrayList<>();
}
