package com.example.ist412group4.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "loanApplication")
public class LoanApplication implements Serializable {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long applicationNo;
    @Column(name = "Applicant Name")
    private String name;
    @Column(name = "Address")
    private String address;
    @Column(name = "Phone Number")
    private String phoneNo;
    @Column(name = "Social Security Number")
    private String social;
    @Column(name = "Type of Loan")
    private String loanType;
    @Column(name = "Date of Birth")
    private String birthDay;
    @Column(name = "Gross Monthly Income")
    private long income;
    @Column(name = "Amount of Loan")
    private BigDecimal loanAmount;


    public long getApplicationNo() { return applicationNo; }

    public void setApplicationNo(long applicationNo) { this.applicationNo = applicationNo; }

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

    public void setBirthDay(String bDay) {
        this.birthDay = birthDay;
    }

    public long getIncome() {
        return income;
    }

    public void setIncome(long income) {
        this.income = income;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

}





