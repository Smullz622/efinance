package com.example.ist412group4.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "application")
public class LoanApplication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long aid;
    @Column(name = "applicant_name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNo;
    @Column(name = "social_security_number")
    private String social;
    @Column(name = "type_of_loan")
    private String loanType;
    @Column(name = "date_of_birth")
    private String birthDay;
    @Column(name = "gross_monthly_income")
    private String income;
    @Column(name = "amount_of_loan")
    private String loanAmount;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "application_customer",
            joinColumns = {@JoinColumn(name = "aid")},
            inverseJoinColumns = {@JoinColumn(name = "id")})

    public Set<Customer> customers = new HashSet<>();

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public long getApplicationNo() { return aid; }

    public void setApplicationNo(long aid) { this.aid = aid; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

}





