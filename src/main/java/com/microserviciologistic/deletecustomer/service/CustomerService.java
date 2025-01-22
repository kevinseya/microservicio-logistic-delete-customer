package com.microserviciologistic.deletecustomer.service;

import com.microserviciologistic.deletecustomer.model.Customer;
import com.microserviciologistic.deletecustomer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void deleteCustomer(Long customerId) {
        try {
            Optional<Customer> existingCustomer = customerRepository.findById(customerId);
            if (existingCustomer.isPresent()) {
                customerRepository.deleteById(customerId);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente con id " + customerId + " no encontrado");
            }
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error de conexión a la base de datos: " + e.getMessage(), e);
        }
    }

}
