package com.example.ist412group4.service;

import com.example.ist412group4.model.Loan;
import com.example.ist412group4.model.LoanApplication;
import com.example.ist412group4.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<Loan> getAllLoans() { return loanRepository.findAll(); }

    @Override
    public void saveLoan(Loan loan) { this.loanRepository.save(loan); }

    @Override
    public Loan getLoanById(long id) {
        Optional<Loan> optional = loanRepository.findById(id);
        Loan loan = null;
        if  (optional.isPresent()) {
            loan = optional.get();
        } else {
            throw new RuntimeException("Loan not found for id :: " + id);
        }
        return loan;
    }

    @Override
    public void deleteLoanById(long id) { this.loanRepository.deleteById(id);}

    @Override
    public boolean validateLoan(Loan loan) {
        if (loan.getLoanType().isBlank() || loan.getTotalValue().isBlank() || (loan.getBalance().isBlank())
                || loan.getPayment().isBlank() || loan.getTerm().isBlank()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Page<Loan> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.loanRepository.findAll(pageable);
    }
}
