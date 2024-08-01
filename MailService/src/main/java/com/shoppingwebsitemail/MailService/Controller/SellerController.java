package com.shoppingwebsitemail.MailService.Controller;

import com.shoppingwebsitemail.MailService.Service.SellerMailService;
import com.shoppingwebsitemail.MailService.dto.request.AddProductDto;
import com.shoppingwebsitemail.MailService.dto.response.GeneralMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail/seller")
public class SellerController {
    @Autowired
    SellerMailService sellerMailservice;
    @PostMapping("/product")
    public ResponseEntity addProduct(@RequestBody AddProductDto addProductDto){

        try {
            sellerMailservice.sendAddProductMail(addProductDto);
            return new ResponseEntity(new GeneralMessage(true), HttpStatus.OK);
        }catch (Exception e){

            return  new ResponseEntity(new GeneralMessage(false),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
