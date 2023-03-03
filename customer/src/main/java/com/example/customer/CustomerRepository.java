package com.example.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    //here i am overriding the findById to be Optional i.e. the value found can be null.
    Optional<Customer> findById(Integer id);

}







