package com.microservice.trainning.controller;

import com.microservice.trainning.model.Foo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FooController {

    @RequestMapping(value = "/foos", method = RequestMethod.GET)
    public ResponseEntity<List<Foo>> foos() {
        return new ResponseEntity<>(Arrays.asList(new Foo(24), new Foo(50)), HttpStatus.OK);
    }

    @RequestMapping(value = "/foos/{id}", method = RequestMethod.GET)
    public ResponseEntity<Foo> foo(@PathVariable("id") int id) {
        return new ResponseEntity<>(new Foo(id), HttpStatus.OK);
    }

}
