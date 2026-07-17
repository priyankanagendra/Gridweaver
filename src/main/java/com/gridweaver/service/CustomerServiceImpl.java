package com.gridweaver.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gridweaver.entity.Customer;
import com.gridweaver.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {

        Customer existingCustomer =
                customerRepository.findById(id).orElse(null);

        if (existingCustomer != null) {
            existingCustomer.setCustomerName(customer.getCustomerName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());

            return customerRepository.save(existingCustomer);
        }

        return null;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}