package com.example.tenantfinder;

public class Property_modal {

    private Property_modal(){}//default constructor
    private String address, no_of_bedroom, no_of_hall, no_of_kitchen, description, name, email, phone;


    public Property_modal(String address, String no_of_bedroom, String no_of_hall, String no_of_kitchen, String description, String name, String email, String phone) {
        this.address = address;
        this.no_of_bedroom = no_of_bedroom;
        this.no_of_hall = no_of_hall;
        this.no_of_kitchen = no_of_kitchen;
        this.description = description;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNo_of_bedroom() {
        return no_of_bedroom;
    }

    public void setNo_of_bedroom(String no_of_bedroom) {
        this.no_of_bedroom = no_of_bedroom;
    }

    public String getNo_of_hall() {
        return no_of_hall;
    }

    public void setNo_of_hall(String no_of_hall) {
        this.no_of_hall = no_of_hall;
    }

    public String getNo_of_kitchen() {
        return no_of_kitchen;
    }

    public void setNo_of_kitchen(String no_of_kitchen) {
        this.no_of_kitchen = no_of_kitchen;
    }
}
