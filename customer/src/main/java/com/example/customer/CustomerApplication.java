package com.example.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class,args);
    }

    @GetMapping("/")
    public GreetResponse greet(){
        return new GreetResponse("Hello World", new Person("Suraj", 24));
    }
    record GreetResponse(String greeting, Person person){
    }

    record Person (String name, Integer age){
    }



}
