package com.example.ist412group4.controller;

import com.example.ist412group4.model.Customer;
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
        return "employee/employee_login";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee/new_employee";
    }

    @GetMapping("/validateEmployeeLogin")
    public String validateEmployeeLogin(@ModelAttribute("employee") Employee employee) {
        if (employeeService.validate(employee)==true){
            return "employee/employee_menu";
        } else {
            return "error_pages/account_not_found_error";
        }
    }

    @GetMapping("/employeeMenu")
    public String viewEmployeeMenu(Model model){
        return "employee/employee_menu";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        if (employee.authenticate() == true) {
            employeeService.saveEmployee(employee);
            return "redirect:/showEmployeeLogin";
        }
        return "error_pages/new_employee_error";
    }
    @GetMapping("/findEmployeePassword")
    public String findEmployeePassword(@ModelAttribute("employee") Employee employee)
    {
        return "employee/employee_find_password";
    }
    @GetMapping("/employeePasswordResult")
    public String customerEmployeeResult(@ModelAttribute("employee") Employee employee) {
        for (Employee e : employeeService.getAllEmployees()) {
            if (employee.getEmpEmail().equals(e.getEmpEmail())) {
                long id = e.getId();
                return "redirect:/showEmployeePassword/" + String.valueOf(id);
            }
        }
        return "error_pages/account_not_found_error";
    }
    @GetMapping("/showEmployeePassword/{id}")
    public String showEmployeePassword(@PathVariable (value = "id") long id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee/show_password";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "redirect:/";
    }

}
