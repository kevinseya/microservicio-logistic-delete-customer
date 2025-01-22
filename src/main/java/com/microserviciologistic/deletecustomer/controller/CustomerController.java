package com.microserviciologistic.deletecustomer.controller;

import com.microserviciologistic.deletecustomer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customers", description = "Endpoints for managing customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete customer", description = "Endpoint to delete a customer by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        try {
            System.out.println("Eliminando cliente con ID: " + id);
            customerService.deleteCustomer(id);
            return ResponseEntity.ok("Customer deleted successfully.");
        } catch (ResponseStatusException e) {
            System.err.println("Error al eliminar el cliente: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error inesperado al eliminar el cliente: " + e.getMessage(), e
            );
        }
    }
}
