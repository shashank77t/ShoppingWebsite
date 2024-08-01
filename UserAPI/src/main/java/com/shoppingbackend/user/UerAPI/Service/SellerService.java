package com.shoppingbackend.user.UerAPI.Service;

import com.shoppingbackend.user.UerAPI.Controller.SellerController;
import com.shoppingbackend.user.UerAPI.DTO.GeneralUserDTO.UserDTO;
import com.shoppingbackend.user.UerAPI.DTO.RequestDTO.AddProductMailDto;
import com.shoppingbackend.user.UerAPI.DTO.ResponseDTO.MailResponseDto;
import com.shoppingbackend.user.UerAPI.DTO.SellerProductRegistrationDTO;
import com.shoppingbackend.user.UerAPI.Exception.MailNotSendException;
import com.shoppingbackend.user.UerAPI.Exception.UnAuthorized;
import com.shoppingbackend.user.UerAPI.Exception.UserDoesNotExistException;
import com.shoppingbackend.user.UerAPI.Utils.ApiUrlUtil;
import com.shoppingbackend.user.UerAPI.Utils.RequestorUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SellerService {
    @Autowired
    RequestorUtil requestorUtil;




    private static final Logger shoppingLogger = Logger.getLogger(SellerService.class.getName());




    public static String generateSellerMailForProduct(String sellerName, String productName, int price, int quantity,String productType) {
        String mailText = String.format("Hi %s,\n" +
                        "Your product got added to our shopping website. Below are your product details:\n" +
                         "0.SellerName: %S\n"+
                        "1. Product Name: %s\n" +
                        "2. Price: %d\n" +
                        "3. Quantity: %d\n" +
                        "4. Product Type: %s\n",
                sellerName, productName, price, quantity,productType);
        return mailText;
    }
    public SellerProductRegistrationDTO register(SellerProductRegistrationDTO sellerProductRegistrationDTO) {
        UUID sellerID = sellerProductRegistrationDTO.getSellerID();
        System.out.println(sellerProductRegistrationDTO);
        String url = "http://localhost:3022/db/user/getuser?id="+sellerID.toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity<>(headers);
        RestTemplate restTemplate=new RestTemplate();
      ResponseEntity<UserDTO> resp =restTemplate.exchange(url, HttpMethod.GET,entity, UserDTO.class);
        UserDTO reee=resp.getBody();
        System.out.println(reee);
     if(resp.getBody()==null){
         throw new UserDoesNotExistException(String.format("User with id %s does not exist in system",sellerID));

     }
     if(!resp.getBody().getType().equals("SELLER")){
          throw new UnAuthorized(String.format("User with id %s does not have access of this operation",sellerID));
     }
     String url2="http://localhost:3022/db/product/add";
     HttpEntity entity2=new HttpEntity(sellerProductRegistrationDTO,headers);

     ResponseEntity<SellerProductRegistrationDTO>resp2=restTemplate.exchange(url2,HttpMethod.POST,entity2,SellerProductRegistrationDTO.class);
     System.out.println(resp2.getBody());
     if(resp2.getBody()==null){
         shoppingLogger.log( Level.FINE,"is this method is getting executed");
         throw new RuntimeException(String.format("Product addition was not successful"));
     }
        //mail service
        String mailUrl= ApiUrlUtil.mailApiURL+"/seller/product";
       String mailText=generateSellerMailForProduct(reee.getName(),sellerProductRegistrationDTO.getProductName(),sellerProductRegistrationDTO.getPrice(),sellerProductRegistrationDTO.getQuantity(),sellerProductRegistrationDTO.getProductType());
        AddProductMailDto addProductMailDto=new AddProductMailDto(reee.getEmail(),mailText,"Congratulations !!products got added ", reee.getName());

        HttpHeaders header=requestorUtil.getHeaders();
        HttpEntity entity1=requestorUtil.getEntity(addProductMailDto,header);
        ResponseEntity<MailResponseDto>mailResp=restTemplate.exchange(mailUrl,HttpMethod.POST,entity1,MailResponseDto.class);
        System.out.println("Hello1");
        if(!mailResp.getBody().isSuccess()){
            System.out.println("Hello2");
            throw new MailNotSendException(String.format("Mail is not sent to the user with id %s",reee.getId()));
        }
        return resp2.getBody();

    }
}