package com.example.ist412group4.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee")
public class Employee extends User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "emp_name")
    private String name;
    @Column(name = "emp_email")
    private String email;
    @Column(name = "emp_password")
    private String password;
    @Column(name = "emp_num")
    private String empNum;

    @Override
    public boolean authenticate(){
        if ((empNum.length() != 5) || name.isBlank()|| email.isBlank()|| password.isBlank()) {
            return true;
        }else {
            return false;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmpNum() {
        return empNum;
    }

    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }
}
