package com.microserviciologistic.deletecustomer.service;

import com.microserviciologistic.deletecustomer.model.Customer;
import com.microserviciologistic.deletecustomer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final WebSocketClientService webSocketClientService;


    @Autowired
    public CustomerService(CustomerRepository customerRepository, WebSocketClientService webSocketClientService) {
        this.customerRepository = customerRepository;
        this.webSocketClientService = webSocketClientService;

    }

    public void deleteCustomer(UUID customerId) {
        try {
            System.out.println(customerId);
            Optional<Customer> existingCustomer = customerRepository.findById(customerId);
            if (existingCustomer.isPresent()) {
                Customer customer = existingCustomer.get();
                System.out.println(customer.getId());
                customerRepository.softDeleteUser(customerId);
                customerRepository.save(customer);
                webSocketClientService.sendEvent("DELETE", customerId);

            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with ID " + customerId + " not found");
            }
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error to connection with database: " + e.getMessage(), e);
        }
    }

}
