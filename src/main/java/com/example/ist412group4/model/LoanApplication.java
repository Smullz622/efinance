package com.example.ist412group4.model;

import javax.persistence.*;
import javax.websocket.ClientEndpoint;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@Entity
@Table(name = "application")
public class LoanApplication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long aid;

    @Column(name = "id")
    private long id;

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
    @Column(name = "application_status")
    private String status;

    @Column(name = "interest")
    private Double interest;

    @Column(name = "term")
    private String term;

    @Column(name = "payment")
    private Double payment;

    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int termToInt() {
        if (this.term.equals("five")){
            return 5;
        } else if (this.term.equals("ten")) {
            return 10;
        } else if (this.term.equals("fifteen")) {
            return 15;
        } else {
            return 20;
        }
    }
    public void setPayment(Double defaultPayment){
        this.payment = defaultPayment;
    }
    public void setPayment(){
        Double a = Double.valueOf(this.loanAmount);
        System.out.println("a::" + a + a.isNaN());
        Double i = this.interest/100;
        System.out.println("i::" + i + i.isNaN());
        Double r = i/12;
        System.out.println("r::" + r + r.isNaN());
        int t = termToInt();
        System.out.println("t::" + t);
        int n = t*12;
        System.out.println("n::" + n);
        Double p = 1 + r;
        System.out.println("p::" + p + p.isNaN());
        Double calc = a*((r*(Math.pow(p, n)))/(Math.pow(p, n)-1));
        System.out.println("calc::" + calc + calc.isNaN());
        BigDecimal bigDecimal = new BigDecimal(calc).setScale(2, RoundingMode.HALF_UP);
        this.payment = bigDecimal.doubleValue();
    }

    public Double getPayment() {
        return payment;
    }
}





