package com.shoppingbackend.user.UerAPI.DTO.GeneralUserDTO;

import lombok.*;

import java.util.UUID;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
        UUID id;
        String name;
        String email;
        String password;
        Long contactNumber;
        String address;
        String type;
}
