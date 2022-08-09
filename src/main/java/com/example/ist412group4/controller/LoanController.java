package com.example.ist412group4.controller;

import com.example.ist412group4.model.Customer;
import com.example.ist412group4.model.Loan;
import com.example.ist412group4.service.CustomerService;
import com.example.ist412group4.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class LoanController {

    @Autowired
    LoanService loanService;

    @Autowired
    CustomerService customerService;

    @GetMapping("/saveLoan")
    public String saveLoan(@ModelAttribute("loan") Loan loan)
    {
        loanService.saveLoan(loan);
        return "redirect:/";
    }

    @GetMapping("/showPaymentDetail/{loanId}")
    public String showPaymentDetail(@PathVariable(value = "loanId") long loanId, Model model){
        Loan loan = loanService.getLoanById(loanId);
        Customer customer = customerService.getCustomerById(loan.getCid());
        model.addAttribute("loan", loan);
        model.addAttribute("customer", customer);
        return "loan/new_payment";
    }

    @PostMapping("/savePayment")
    public String savePayment(@ModelAttribute("loan") Loan loan){
        if (loan.getCurrentPayment()>loan.getPayment()){
            loan.setBalance(loan.getBalance()-loan.getCurrentPayment());
            loanService.saveLoan(loan);
            return "redirect:/showCustomerLoans/" + loan.getCid();
        } else {
            return "error_pages/invalid_payment";
        }
        //if payment greater than min payment, run payment method to subtract payment amount from balance
        //if not, throw to error page which will later return them to the show loans page
    }
    @GetMapping("/deleteLoan/{loanId}")
    public String deleteLoanApp(@PathVariable (value = "loanId") long loanId) {
        Loan loan = loanService.getLoanById(loanId);
        Customer customer = customerService.getCustomerById(loan.getCid());
        loanService.deleteLoanById(loanId);
        return "redirect:/showCustomerLoans/" + String.valueOf(customer.getId());
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Loan> page = loanService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Loan> listLoans = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listloans", listLoans);
        return "loan_application/list_of_loan_applications";
    }
}
