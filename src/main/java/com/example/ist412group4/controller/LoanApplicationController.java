package com.example.ist412group4.controller;


import com.example.ist412group4.model.Employee;
import com.example.ist412group4.model.LoanApplication;
import com.example.ist412group4.model.Customer;
import com.example.ist412group4.service.LoanAppService;
import com.example.ist412group4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoanApplicationController {

    @Autowired
    CustomerService customerService;

    @Autowired
    LoanAppService loanAppService;

    @GetMapping("/showNewApplicationForm")
    public String showNewApplicationForm(Model model){
        LoanApplication loanApplication = new LoanApplication();
        model.addAttribute("application", loanApplication);
        return "new_loan_application";
    }

    @PostMapping("/saveLoanApplication")
    public String saveLoanApplication(@ModelAttribute("application") LoanApplication loanApplication){
        if (loanAppService.validateApplication(loanApplication)) {
            loanAppService.saveLoanApplication(loanApplication);
            return "/application_confirmation";
        } else {
            return "/application_error";
        }

    }

}
