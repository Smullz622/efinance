package com.example.ist412group4.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Customer extends User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "cust_name")
    private String name;
    @Column(name = "cust_email")
    private String email;
    @Column(name = "cust_password")
    private String password;

    @Override
    public boolean authenticate() {
        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            return false;
        }else {
            return true;
        }
    }
}
