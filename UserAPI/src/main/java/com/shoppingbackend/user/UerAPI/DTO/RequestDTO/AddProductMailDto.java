package com.shoppingbackend.user.UerAPI.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddProductMailDto {

    String emailId;
    String mailMessage;
    String subjectLine;
    String userName;
}
