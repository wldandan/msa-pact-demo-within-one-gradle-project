package com.microservice.trainning.controller;

import com.microservice.trainning.gateway.FooClient;
import com.microservice.trainning.model.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FooController {

    @Autowired
    FooClient fooClient;

    @RequestMapping("/foos")
    public List<Foo> foos() {
        return fooClient.getFoos();
    }

    @RequestMapping("/")
    public String hello() {
        return "Hello World!";
    }

}
