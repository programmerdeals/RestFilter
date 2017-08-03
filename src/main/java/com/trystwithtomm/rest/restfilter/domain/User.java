package com.trystwithtomm.rest.restfilter.domain;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("user")
public class User {

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    private String firstName;
    private String lastName;
    private Address address;
}
