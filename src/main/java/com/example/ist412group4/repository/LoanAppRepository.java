package com.example.ist412group4.repository;

import com.example.ist412group4.model.Customer;
import com.example.ist412group4.model.Employee;
import com.example.ist412group4.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

public interface LoanAppRepository extends JpaRepository<LoanApplication, Long>{
    @Override
    Optional<LoanApplication> findById(Long id);
}
