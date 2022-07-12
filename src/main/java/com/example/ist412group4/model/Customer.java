package com.example.ist412group4.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "cust_email")
    private String custEmail;
    @Column(name = "cust_password")
    private String custPassword;

    public boolean authenticate() {
        if (custName.isBlank() || custEmail.isBlank() || custPassword.isBlank()) {
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

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustPassword() {
        return custPassword;
    }

    public void setCustPassword(String custPassword) {
        this.custPassword = custPassword;
    }
}
