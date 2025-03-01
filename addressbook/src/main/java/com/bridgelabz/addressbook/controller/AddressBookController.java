package com.bridgelabz.addressbook.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class AddressBookController {
    @GetMapping("/sample")
    public String getHello(){
        return "Hello This is GET Request";
    }
    @PostMapping("/sample")
    public String postHello(){
        return "Hello This is POST Request";
    }
    @PutMapping("/sample")
    public String putHello(){
        return "Hello THis is PUT Request";
    }
    @DeleteMapping("/sample")
    public String deleteHello(){
        return "Hello This is DELETE Request";
    }
}
