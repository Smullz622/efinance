package com.example.ist412group4.repository;

import com.example.ist412group4.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Override
    Optional<Loan> findById(Long id);
}