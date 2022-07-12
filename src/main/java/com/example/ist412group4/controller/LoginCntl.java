package com.example.ist412group4.controller;


import com.example.ist412group4.model.Employee;
import com.example.ist412group4.model.Customer;
import com.example.ist412group4.service.CustomerService;
import com.example.ist412group4.model.User;
import com.example.ist412group4.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.management.MonitorInfo;

@Controller
public class LoginCntl {

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        //model.addAttribute("listCourses", courseService.getAllCourses());
        return "index";
    }

    @GetMapping("/showEmployeeLogin")
    public String viewEmployeeLogin(Model model) {
        //model.addAttribute();
        return "employee_login";
    }

    @GetMapping("/validateEmployeeLogin")
    public String validateEmployeeLogin(@ModelAttribute("employee") Employee employee) {
        if (employeeService.validate(employee)==true){
            return "employee_menu";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/employeeMenu")
    public String viewEmployeeMenu(Model model){
       return "employee_menu";
        //if user type == employee, call authenticate then return "employee_menu"
    }

    @GetMapping("/customerMenu")
    public String viewCustomerMenu(Model model) {
        //validate log in, check that customer is in repository, if not redirect to /
        return "customer_menu";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        if (customer.authenticate() == true) {
            customerService.saveCustomer(customer);
        }
        return "redirect:/customer_login";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        if (employee.authenticate() == true) {
            employeeService.saveEmployee(employee);
        }
        return "redirect:/employee_login";
    }
}
