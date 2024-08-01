package com.shoppingbackend.user.UerAPI.Controller;

import com.shoppingbackend.user.UerAPI.DTO.RequestDTO.OrderDTO;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/buyer")
public class BuyerController {


    @PostMapping()
    public ResponseEntity placeOrder(@RequestBody  OrderDTO orderDTO){

    }

}
