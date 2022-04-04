package com.example.tenantfinder;

public class Property_modal {
    private String deschead, addresshead, contacthead, phonehead, emailhead, description, address, contact_email;
    private int phone_no;

    public Property_modal(String deschead, String addresshead, String contacthead, String phonehead, String emailhead, String description, String address, String contact_email, int phone_no) {
        this.deschead = deschead;
        this.addresshead = addresshead;
        this.contacthead = contacthead;
        this.phonehead = phonehead;
        this.emailhead = emailhead;
        this.description = description;
        this.address = address;
        this.contact_email = contact_email;
        this.phone_no = phone_no;
    }

    public String getDeschead() {
        return deschead;
    }

    public String getAddresshead() {
        return addresshead;
    }

    public String getContacthead() {
        return contacthead;
    }

    public String getPhonehead() {
        return phonehead;
    }

    public String getEmailhead() {
        return emailhead;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getContact_email() {
        return contact_email;
    }

    public int getPhone_no() {
        return phone_no;
    }
}
