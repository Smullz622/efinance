package com.example.ist412group4.service;

import com.example.ist412group4.model.LoanApplication;

import java.util.List;

public interface LoanAppService {
    List<LoanApplication> getAllLoanApplications();
    void saveLoanApplication(LoanApplication loanApplication);
    LoanApplication getLoanApplicationById(long id);
    void deleteLoanApplicationById(long id);
}
