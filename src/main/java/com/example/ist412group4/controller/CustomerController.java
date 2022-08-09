package com.example.ist412group4.controller;


import com.example.ist412group4.model.Customer;
import com.example.ist412group4.model.Loan;
import com.example.ist412group4.model.LoanApplication;
import com.example.ist412group4.service.CustomerService;
import com.example.ist412group4.service.LoanAppService;
import com.example.ist412group4.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    LoanAppService loanAppService;

    @Autowired
    LoanService loanService;

    @GetMapping("/showCustomerLogin")
    public String viewCustomerLogin(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer/customer_login";
    }
    @GetMapping("/showNewCustomerForm")
    public String showNewCustomerForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer/new_customer";
    }

    @GetMapping("/validateCustomerLogin")
    public String validateCustomerLogin(@ModelAttribute("customer") Customer customer) {
        if (customerService.validate(customer)==true){
            Customer currentCust = customerService.getCustomerByLogin(customer);
            long id = currentCust.getId();
            System.out.println("Customer " + customer.toString());
            System.out.println("id " + id);
            return "redirect:/customerMenu/" + String.valueOf(id);
        } else {
            return "error_pages/account_not_found_error";
        }
    }
    @GetMapping("/customerMenu/{id}")
    public String viewCustomerMenu(@PathVariable (value = "id") long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer/customer_menu";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        if (customer.authenticate() == true) {
            customerService.saveCustomer(customer);
            return "redirect:/showCustomerLogin";
        }
        return "error_pages/new_customer_error";
    }
    @GetMapping("/findCustomerPassword")
    public String findCustomerPassword(@ModelAttribute("customer") Customer customer)
    {
        return "customer/customer_find_password";
    }
    @GetMapping("/customerPasswordResult")
    public String customerPasswordResult(@ModelAttribute("customer") Customer customer) {
        for (Customer c : customerService.getAllCustomers()) {
            if (customer.getCustEmail().equals(c.getCustEmail())) {
                long id = c.getId();
                return "redirect:/showCustomerPassword/" + String.valueOf(id);
            }
        }
        return "error_pages/account_not_found_error";
    }
    @GetMapping("/showCustomerPassword/{id}")
    public String showCustomerPassword(@PathVariable (value = "id") long id, Model model){
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer/show_password";
    }

    @GetMapping("/showCustomerLoans/{id}")
    public String showCustomerLoans(@PathVariable (value = "id") long id, Model model){
        Customer customer = customerService.getCustomerById(id);
        List<LoanApplication> allLoanApps = loanAppService.getAllLoanApplications();
        List<LoanApplication> loanAppList = new ArrayList<LoanApplication>();
        for (LoanApplication loanApp : allLoanApps){
            if (loanApp.getId()==id){ loanAppList.add(loanApp);}
            //expand if to check if loan already exists (check loan app status, if accepted)
        }
        model.addAttribute("loanAppList", loanAppList);
        model.addAttribute("customer", customer);

        List<Loan> allLoans = loanService.getAllLoans();
        List<Loan> loanList = new ArrayList<Loan>();
        for (Loan loan : allLoans){
            if (loan.getCid()==id){ loanList.add(loan);}
        }
        model.addAttribute("loanList", loanList);


        return "customer/show_loans";
    }

    @GetMapping("/showTermsReview/{aid}")
    public String showTermReview(@PathVariable (value = "aid") long aid, Model model) {
        LoanApplication loanApplication = loanAppService.getLoanApplicationById(aid);
        Customer customer = customerService.getCustomerById(loanApplication.getId());
        model.addAttribute("customer", customer);
        model.addAttribute("loanApplication", loanApplication);
        return "customer/term_review";
    }

    @PostMapping("/termDecision/{aid}")
    public String termDecision(@PathVariable (value = "aid") long aid, Model model,
                               @ModelAttribute("application") LoanApplication loanApplication){
        Customer customer = customerService.getCustomerById(loanApplication.getId());
        model.addAttribute("customer", customer);
        System.out.println(("Check one " + loanApplication.getPayment()));
        if (loanAppService.validateApplication(loanApplication)) {
            loanApplication.setPayment();
            loanAppService.saveLoanApplication(loanApplication);
            System.out.println(("Check two " + loanApplication.getPayment()));
            if (loanApplication.getStatus().equals("Accepted")){
                System.out.println(("Check three " + loanApplication.getPayment()));
                Loan loan = new Loan();
                loan.setLoanType(loanApplication.getLoanType());
                loan.setTotalValue(loanApplication.getLoanAmount());
                loan.setBalance(loanApplication.getLoanAmount());
                loan.setTerm(loanApplication.getTerm());
                loan.setStatus("In Repayment");
                loan.setInterest(loanApplication.getInterest());
                loan.setPayment(loanApplication.getPayment());
                loan.setCid(loanApplication.getId());
                loanService.saveLoan(loan);
                System.out.println(("Check four " + loanApplication.getPayment()));
            }
            return "redirect:/showCustomerLoans/" + String.valueOf(customer.getId());
        }
        else {
            return "error_pages/application_error";
        }
    }

}


