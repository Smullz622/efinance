package com.example.ist412group4.service;

import com.example.ist412group4.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);
    boolean validate(Employee employee);
}
