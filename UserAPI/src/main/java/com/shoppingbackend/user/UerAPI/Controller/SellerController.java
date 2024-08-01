package com.shoppingbackend.user.UerAPI.Controller;

import com.shoppingbackend.user.UerAPI.DTO.GeneralUserDTO.GeneralMessageDTO;
import com.shoppingbackend.user.UerAPI.DTO.SellerProductRegistrationDTO;
import com.shoppingbackend.user.UerAPI.Exception.MailNotSendException;
import com.shoppingbackend.user.UerAPI.Exception.UnAuthorized;
import com.shoppingbackend.user.UerAPI.Exception.UserDoesNotExistException;
import com.shoppingbackend.user.UerAPI.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user/seller")
public class SellerController {

    // Create logger
    private static final Logger shoppingLogger = Logger.getLogger(SellerController.class.getName());

    @Autowired
    SellerService sellerService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody SellerProductRegistrationDTO sellerProductRegistrationDTO) {
        try {
            SellerProductRegistrationDTO resp = sellerService.register(sellerProductRegistrationDTO);
            return new ResponseEntity(resp, HttpStatus.CREATED);
        } catch (UnAuthorized unAuthorized) {
            shoppingLogger.log(Level.WARNING, "UnAuthorized Exception: " + unAuthorized.getMessage());
            return new ResponseEntity(new GeneralMessageDTO(unAuthorized.getMessage()), HttpStatus.UNAUTHORIZED);
        } catch (UserDoesNotExistException userDoesNotExistException) {
            shoppingLogger.log(Level.WARNING, "UserDoesNotExist Exception: " + userDoesNotExistException.getMessage());
            return new ResponseEntity(new GeneralMessageDTO(userDoesNotExistException.getMessage()), HttpStatus.NOT_FOUND);
        } catch (MailNotSendException mailNotSendException) {
            shoppingLogger.log(Level.SEVERE, "MailNotSend Exception: " + mailNotSendException.getMessage());
            return new ResponseEntity(new GeneralMessageDTO(mailNotSendException.getMessage()), HttpStatus.BAD_GATEWAY);
        } catch (RuntimeException runtimeException) {
            shoppingLogger.log(Level.SEVERE, "RuntimeException: lkll" + runtimeException.getMessage());
            return new ResponseEntity(new GeneralMessageDTO(runtimeException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
