package com.example.ist412group4.controller;


import com.example.ist412group4.model.Employee;
import com.example.ist412group4.model.Customer;
import com.example.ist412group4.service.CustomerService;
import com.example.ist412group4.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

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
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee_login";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
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

    @GetMapping("/showCustomerLogin")
    public String viewCustomerLogin(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer_login";
    }
    @GetMapping("/showNewCustomerForm")
    public String showNewCustomerForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "new_customer";
    }

    @GetMapping("/validateCustomerLogin")
    public String validateCustomerLogin(@ModelAttribute("customer") Customer customer) {
        if (customerService.validate(customer)==true){
            return "customer_menu";
        } else {
            return "redirect:/";
        }
    }
    @GetMapping("/customerMenu")
    public String viewCustomerMenu(Model model) {
        return "customer_menu";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        if (customer.authenticate() == true) {
            customerService.saveCustomer(customer);
            return "redirect:/showCustomerLogin";
        }
        return "new_customer";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        if (employee.authenticate() == true) {
            employeeService.saveEmployee(employee);
            return "redirect:/showEmployeeLogin";
        }
        return "new_employee";
    }
}
