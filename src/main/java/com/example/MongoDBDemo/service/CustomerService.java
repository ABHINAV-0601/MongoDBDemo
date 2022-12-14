package com.example.MongoDBDemo.service;

import com.example.MongoDBDemo.domain.Customer;
import com.example.MongoDBDemo.exception.CustomerNotfoundException;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> gatAllCustomerData() throws Exception;
    boolean deleteCustomer(int customerId) throws CustomerNotfoundException;
    List<Customer> getAllCustomersByCity(String city) throws CustomerNotfoundException;

}
