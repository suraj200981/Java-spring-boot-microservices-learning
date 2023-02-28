package com.example.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class CustomerApplication implements CommandLineRunner {

    //inject customer repository
    private final CustomerRepository customerRepository;

    public CustomerApplication(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class,args);
    }
    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }


    @Override
    public void run(String... args) throws Exception {
        Customer customer1 = new Customer(1,"John", "Doe", "john.doe@example.com", 25);
        customerRepository.save(customer1);
    }
}
