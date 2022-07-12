package com.example.ist412group4.model;

public class UserFactory {
    public User getUser(String userType) {
        if (userType == null) {
            return null;
        } else if (userType == "employee") {
            return new Employee();
        } else if (userType == "customer") {
            return new Customer();
        }
        return null;
    }
}
