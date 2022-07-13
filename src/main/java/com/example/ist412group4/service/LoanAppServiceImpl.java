package com.example.ist412group4.service;

import java.util.List;
import java.util.Optional;

import com.example.ist412group4.model.LoanApplication;
import com.example.ist412group4.repository.LoanAppRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LoanAppServiceImpl implements LoanAppService{

    @Autowired
    private LoanAppRepository loanAppRepository;

    @Override
    public List<LoanApplication> getAllLoanApplications() { return loanAppRepository.findAll(); }

    @Override
    public void saveLoanApplication(LoanApplication loanApplication) { this.loanAppRepository.save(loanApplication); }

    @Override
    public LoanApplication getLoanApplicationById(long id) {
        Optional<LoanApplication> optional = loanAppRepository.findById(id);
        LoanApplication loanApplication = null;
        if  (optional.isPresent()) {
            loanApplication = optional.get();
        } else {
            throw new RuntimeException("Application not found for id :: " + id);
        }
        return loanApplication;
    }

    @Override
    public void deleteLoanApplicationById(long id) { this.loanAppRepository.deleteById(id);}
}
