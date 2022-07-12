package com.example.ist412group4.service;

import java.util.List;
import java.util.Optional;
import com.example.ist412group4.model.Customer;
import com.example.ist412group4.model.Employee;
import com.example.ist412group4.repository.CustomerRepository;
import com.example.ist412group4.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException("Employee not found for id :: " + id);
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteById(id);
    }

    public boolean validate(Employee emp1) {
        for (Employee e : this.employeeRepository.findAll()) {
            if (emp1.getEmpEmail().equals(e.getEmpEmail()) && emp1.getEmpNum().equals(e.getEmpNum()) && (emp1.getEmpPassword().equals(e.getEmpPassword()))) {
                return true;
            }
        } return false;
    }
}
