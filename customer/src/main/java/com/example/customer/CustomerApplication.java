package com.example.customer;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @DeleteMapping
    public void deleteCustomer(@RequestParam Integer id){
        customerRepository.deleteById(id);
    }

    @PostMapping("/update")
    public void updateCustomer(@RequestBody NewCustomerRequest request, @RequestParam Integer id){

        Customer currentCus = new Customer();


        //find customer by ID, i am using Optional as it is a way to handle Null objects
        //because the value of customer with the specific id could be null
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            currentCus= customer.get();
        } else {
            throw new EntityNotFoundException("Customer with id " + id + " not found");
        }

        currentCus.setFirstName(request.firstName);
        currentCus.handleUpdate();
        customerRepository.save(currentCus);




    }



    @Override
    public void run(String... args) throws Exception {
        Customer customer1 = new Customer(1,"John", "Doe", "john.doe@example.com", 25);
        customerRepository.save(customer1);
    }
}
