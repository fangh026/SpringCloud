package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/payment")
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverport;

    @GetMapping("/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id){
        return "nacos registry, Serverport: "+ serverport+"\t id: "+id;
    }
}
