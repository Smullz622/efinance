package com.example.ist412group4.controller;


import com.example.ist412group4.model.Customer;
import com.example.ist412group4.model.LoanApplication;
import com.example.ist412group4.service.LoanAppService;
import com.example.ist412group4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoanApplicationController {

    @Autowired
    CustomerService customerService;

    @Autowired
    LoanAppService loanAppService;

    @GetMapping("/showNewApplicationForm/{id}")
    public String showNewApplicationForm(@PathVariable(value = "id") long id, Model model){
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        LoanApplication loanApplication = new LoanApplication();
        model.addAttribute("application", loanApplication);
        return "loan_application/new_loan_application";
    }

    @PostMapping("/saveLoanApplication")
    public String saveLoanApplication(@ModelAttribute("application") LoanApplication loanApplication){
        if (loanAppService.validateApplication(loanApplication)) {
            loanAppService.saveLoanApplication(loanApplication);
            return "loan_application/application_confirmation";
        } else {
            return "error_pages/application_error";
        }

    }

}
