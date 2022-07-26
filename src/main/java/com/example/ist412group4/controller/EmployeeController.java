package com.example.ist412group4.controller;

import com.example.ist412group4.model.Employee;
import com.example.ist412group4.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class EmployeeController {

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
            return "employee_login_error";
        }
    }

    @GetMapping("/employeeMenu")
    public String viewEmployeeMenu(Model model){
        return "employee_menu";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        if (employee.authenticate() == true) {
            employeeService.saveEmployee(employee);
            return "redirect:/showEmployeeLogin";
        }
        return "new_employee_error";
    }

    private long result = 1;

    @GetMapping("/findEmployeePassword")
    public String findEmployeePassword(@ModelAttribute("employee") Employee employee)
    {
        long id = employee.getId();
        result = (id == 0) ? 1 : id;
        Employee test = employeeService.getEmployeeById(result);
        return "employee_find_password";
    }

    @GetMapping("/findCustomerPassword")
    public String findCustomerPassword(@ModelAttribute("customer") Customer customer)
    {
        long id = customer.getId();
        result = (id == 0) ? 1 : id;
        Customer test = customerService.getCustomerById(result);
        return "customer_find_password";
    }

    @GetMapping("/employeePasswordResult")
    public String employeePasswordResult(@ModelAttribute("employee") Employee employee)
    {
        this.employeeService.getEmployeeById(result);

        return "employee_password_result";
    }

    @GetMapping("/customerPasswordResult")
    public String customerPasswordResult(@ModelAttribute("customer") Customer customer) {
        this.customerService.getCustomerById(result);
        return "customer_password_result";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "redirect:/";
    }

}
