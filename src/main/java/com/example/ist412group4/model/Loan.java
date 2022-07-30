package com.example.ist412group4.model;


import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Loans")
public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loanId;

    @Column(name = "loan_type")
    private String loanType;
    @Column(name = "total_value")
    private String totalValue;
    @Column(name = "balance")
    private String balance;
    @Column(name = "monthly_payment")
    private String payment;
    @Column(name = "term")
    private String term;
    @Column(name = "status")
    private String status;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "loan_customer",
            joinColumns = {@JoinColumn(name = "loanId")},
            inverseJoinColumns = {@JoinColumn(name = "id")})

    public Set<Customer> customers = new HashSet<>();

    public Set<Customer> getCustomers() {
        return customers;
    }

    public long getLoanId() {
        return loanId;
     }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }





}
