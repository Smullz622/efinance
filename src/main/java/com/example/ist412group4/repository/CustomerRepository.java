package com.example.ist412group4.repository;

import com.example.ist412group4.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    @Override
    Optional<Customer> findById(Long id);
}
