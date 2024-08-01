package com.shoppingbackend.user.UerAPI.Utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class RequestorUtil {
    public  HttpHeaders getHeaders(){
      HttpHeaders headers=new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      return headers;
    }
    public  RestTemplate getTemplate(){
        return new RestTemplate();
    }
    public  HttpEntity getEntity(HttpHeaders headers){
       HttpEntity entity=new HttpEntity(headers);
       return entity;
    }
    public  HttpEntity getEntity(Object ob,HttpHeaders headers){
        HttpEntity entity=new HttpEntity(ob,headers);
        return entity;
    }
}
