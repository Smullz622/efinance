package com.example.ist412group4.controller;


import com.example.ist412group4.model.Customer;
import com.example.ist412group4.model.Loan;
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

    @PostMapping("/saveLoanApplication/{id}")
    public String saveLoanApplication(@PathVariable(value = "id") long id, Model model,
                                      @ModelAttribute("application") LoanApplication loanApplication){
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        if (loanAppService.validateApplication(loanApplication)) {
            loanAppService.saveLoanApplication(loanApplication);
            return "loan_application/application_confirmation";
        } else {
            return "error_pages/application_error";
        }
    }

    @PostMapping("/updateLoanApplicationStatus")
    public String updateLoanApplicationStatus(@ModelAttribute("application") LoanApplication loanApplication){
        if (loanAppService.validateApplication(loanApplication)) {
            loanAppService.saveLoanApplication(loanApplication);
            return "loan_application/status_update_confirmation";
        } else {
            return "error_pages/status_update_error";
        }
    }

    @GetMapping("/showApplications")
    public String showApplications(Model model)
    {
        model.addAttribute("listLoanApps", loanAppService.getAllLoanApplications());
        return "loan_application/list_of_loan_applications";
    }

    @GetMapping("/deleteLoanApp/{id}")
    public String deleteLoanApp(@PathVariable (value = "id") long id) {
        this.loanAppService.deleteLoanApplicationById(id);
        return "redirect:/employeeMenu";
    }

    @GetMapping("/showApplicationForReview/{id}")
    public String showApplicationForReview(@PathVariable (value = "id") long id, Model model) {
        LoanApplication loanApplication = loanAppService.getLoanApplicationById(id);
        model.addAttribute("loanApplication", loanApplication);
        return "loan_application/update_application";
    }

}
