package com.example.MongoDBDemo.controller;

import com.example.MongoDBDemo.domain.Customer;
import com.example.MongoDBDemo.exception.CustomerNotfoundException;
import com.example.MongoDBDemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("custData/api/")
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping(value = "/customer")
    public ResponseEntity<?> insertCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.CREATED);
    }
    @GetMapping(value = "/customers")
    public ResponseEntity<?> fetchAllCustomers(){
        ResponseEntity responseEntity =null;
        try {
           responseEntity= new ResponseEntity<>(customerService.gatAllCustomerData(),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }
    @DeleteMapping(value = "/customer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int customerId) throws CustomerNotfoundException {
        ResponseEntity responseEntity =null;
        try {
            customerService.deleteCustomer(customerId);
            responseEntity = new ResponseEntity<>("successfully deleted one record",HttpStatus.OK);
        } catch (CustomerNotfoundException e) {
            throw new CustomerNotfoundException();
        }catch (Exception e){
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("customer/{city}")
    public ResponseEntity<?> fetchByCity(@PathVariable String city){
        ResponseEntity responseEntity=null;
        try{
            responseEntity=new ResponseEntity(customerService.getAllCustomersByCity(city),HttpStatus.FOUND);

        } catch (CustomerNotfoundException e) {
            responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
