package com.example.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

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

    record NewCustomerRequest(String firstName, String lastName, String email, Integer age){


    }
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        System.out.printf(request.firstName, "firstname is this");
        Customer customer = new Customer();
        customer.setFirstName(request.firstName);
        customer.setLastName(request.lastName);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customerRepository.save(customer);

    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer1 = new Customer(1,"John", "Doe", "john.doe@example.com", 25);
        customerRepository.save(customer1);
    }
}
