package com.example.ist412group4.service;

import com.example.ist412group4.model.Loan;
import com.example.ist412group4.model.LoanApplication;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LoanService {
    List<Loan> getAllLoans();
    void saveLoan(Loan loan);
    Loan getLoanById(long id);
    void deleteLoanById(long id);

    boolean validateLoan(Loan loan);
    Page<Loan> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

