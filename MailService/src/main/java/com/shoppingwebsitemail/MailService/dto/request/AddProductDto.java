package com.shoppingwebsitemail.MailService.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddProductDto {
    String emailId;
    String mailMessage;
    String subjectLine;
    String userName;
}
