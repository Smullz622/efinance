package com.example.ist412group4.repository;

import com.example.ist412group4.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    @Override
    Optional<Employee> findById(Long id);
}