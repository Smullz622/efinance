package com.example.ist412group4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
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

    @ManyToMany(mappedBy = "customers")
    @JsonIgnore
    private Set<LoanApplication> loanApplications = new HashSet<>();

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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", custName='" + custName + '\'' +
                ", custEmail='" + custEmail + '\'' +
                ", custPassword='" + custPassword + '\'' +
                ", loanApplications=" + loanApplications +
                '}';
    }
}
