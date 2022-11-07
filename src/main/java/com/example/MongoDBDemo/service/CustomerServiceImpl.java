package com.example.MongoDBDemo.service;

import com.example.MongoDBDemo.domain.Customer;
import com.example.MongoDBDemo.exception.CustomerNotfoundException;
import com.example.MongoDBDemo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> gatAllCustomerData() throws Exception {

        return customerRepository.findAll();
    }

    @Override
    public boolean deleteCustomer(int customerId) throws CustomerNotfoundException {
        boolean flag=false;
        if(customerRepository.findById(customerId).isEmpty()){
            throw new CustomerNotfoundException();
        }
        else{
            customerRepository.deleteById(customerId);
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Customer> getAllCustomersByCity(String city) throws CustomerNotfoundException {
        if(customerRepository.findAllCustomersFromCity(city).isEmpty()){
            throw new CustomerNotfoundException();
        }
        return customerRepository.findAllCustomersFromCity(city);
    }
}
