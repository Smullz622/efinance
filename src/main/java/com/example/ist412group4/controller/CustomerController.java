package com.example.ist412group4.controller;


import com.example.ist412group4.model.Customer;
import com.example.ist412group4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

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
            return "customer/customer_menu";
        } else {
            return "error_pages/account_not_found_error";
        }
    }
    @GetMapping("/customerMenu")
    public String viewCustomerMenu(Model model) {
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
    private long result = 1;
    @GetMapping("/findCustomerPassword")
    public String findCustomerPassword(@ModelAttribute("customer") Customer customer)
    {
        for (Customer c : customerService.getAllCustomers()) {
            if (customer.getCustEmail().equals(c.getCustEmail())) {
                long id = customer.getId();
                result = (id == 0) ? 1 : id;
                System.out.println("Result: " + result);
                return "customer/customer_find_password";
            }
        }
        return "error_pages/account_not_found_error";
    }
    @GetMapping("/customerPasswordResult")
    public String customerPasswordResult(@ModelAttribute("customer") Customer customer) {
        this.customerService.getCustomerById(result);
        return "customer/customer_password_result";
    }

}


