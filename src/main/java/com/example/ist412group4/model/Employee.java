package com.example.ist412group4.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "emp_name")
    private String empName;
    @Column(name = "emp_email")
    private String empEmail;
    @Column(name = "emp_password")
    private String empPassword;
    @Column(name = "emp_num")
    private String empNum;

    public boolean authenticate(){
        if ((empNum.length() != 5) || empName.isBlank()|| empEmail.isBlank()|| empPassword.isBlank()) {
            return false;
        }else {
            return true;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public String getEmpNum() {
        return empNum;
    }

    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }
}
