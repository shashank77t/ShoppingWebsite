package com.shoppingwebsitemail.MailService.Service;

import com.shoppingwebsitemail.MailService.Utils.MailUtils;
import com.shoppingwebsitemail.MailService.dto.request.AddProductDto;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SellerMailService {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    MailUtils mailUtils;

    public void sendAddProductMail(AddProductDto addProductDto) throws Exception{
        System.out.println(addProductDto);
        String emailId=addProductDto.getEmailId();
        String subject=addProductDto.getSubjectLine();
        MimeMessage message=mailUtils.getMimeMessage();
        MimeMessageHelper messageHelper=mailUtils.getMimeMessageHelper();
        try{
         //   messageHelper.setFrom("shashank.yemjal@gmail.com");
            messageHelper.setTo(emailId);
            messageHelper.setSubject(subject);
            messageHelper.setText(addProductDto.getMailMessage());
        //    JavaMailSender mailSender=mailUtils.getJavaMailSender();
          mailSender.send(message);

        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
