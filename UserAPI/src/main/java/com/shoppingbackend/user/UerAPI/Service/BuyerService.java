package com.shoppingbackend.user.UerAPI.Service;

import com.shoppingbackend.user.UerAPI.DTO.RequestDTO.AddOrderRequestBody;
import com.shoppingbackend.user.UerAPI.DTO.RequestDTO.OrderDTO;
import com.shoppingbackend.user.UerAPI.DTO.RequestDTO.OrderProductDTO;
import com.shoppingbackend.user.UerAPI.DTO.ResponseDTO.OrderDetailsResponseBody;
import com.shoppingbackend.user.UerAPI.DTO.ResponseDTO.ProductResponseBody;
import com.shoppingbackend.user.UerAPI.Exception.UnavailableProduct;
import com.shoppingbackend.user.UerAPI.Utils.ApiUrlUtil;
import com.shoppingbackend.user.UerAPI.Utils.RequestorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BuyerService {
    @Autowired
    RequestorUtil requestorUtil;
    // we will call db api one by one to get all products details according to every product we will check are we abe to place order or not
    //If any product is having the less quantity our api will provide response accordingly
      public void orderProduct(OrderDTO orderDTO){
           List<OrderProductDTO> orderProducts=new ArrayList<>();
           List<ProductResponseBody>products=new ArrayList<>();
           int totalPrice=0;
           int totalQuantity=0;
          for(OrderProductDTO orderProductDTO:orderProducts){
              UUID productId=orderProductDTO.getProductID();
              String url= ApiUrlUtil.dpApiURL+"/product/get?productId="+orderProductDTO.getProductID().toString();
              HttpHeaders headers= requestorUtil.getHeaders();
              RestTemplate template=requestorUtil.getTemplate();
              HttpEntity entity=requestorUtil.getEntity(headers);

              ResponseEntity<ProductResponseBody>r=template.exchange(url, HttpMethod.GET,entity,ProductResponseBody.class);
              ProductResponseBody p=r.getBody();
              if(p.getQuantity()<orderProductDTO.getQuantity()){
                  throw new UnavailableProduct(String.format("Product %s is having the quantity %d",p.getProductName(),p.getQuantity()));
              }
            products.add(r.getBody());
              totalPrice+=p.getPrice()*orderProductDTO.getQuantity();
              totalQuantity+=p.getQuantity();
          }
          //creating one order
          //update order vs product table
          String orderUrl=ApiUrlUtil.dpApiURL+"/order/add";
          HttpHeaders orderheader=requestorUtil.getHeaders();
          AddOrderRequestBody addOrderRequestBody=new AddOrderRequestBody(orderDTO.getUserID(),totalQuantity,totalPrice);
          HttpEntity entity1=requestorUtil.getEntity(addOrderRequestBody,orderheader);
          RestTemplate restTemplate=requestorUtil.getTemplate();
          ResponseEntity<OrderDetailsResponseBody>orderResp=restTemplate.exchange(orderUrl, HttpMethod.POST,entity1, OrderDetailsResponseBody.class);
          OrderDetailsResponseBody order=orderResp.getBody();

      }
}
