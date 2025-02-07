package com.microserviciologistic.deletecustomer.repository;
import com.microserviciologistic.deletecustomer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    @Modifying
    @Transactional
    @Query("UPDATE com.microserviciologistic.deletecustomer.model.Customer u SET u.active = false WHERE u.id = :id")
    void softDeleteUser(@Param("id") UUID id);

}