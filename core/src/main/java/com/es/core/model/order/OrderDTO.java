package com.es.core.model.order;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class OrderDTO {
    @NotEmpty(message = "The value is required")
    private String firstName;
    @NotEmpty(message = "The value is required")
    private String lastName;
    @NotEmpty(message = "The value is required")
    @Size(min = 9, max = 12)
    private String contactPhoneNo;
    @NotEmpty(message = "The value is required")
    private String deliveryAddress;

    public OrderDTO() {
    }

    public OrderDTO(String firstName, String lastName, String contactPhoneNo, String deliveryAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactPhoneNo = contactPhoneNo;
        this.deliveryAddress = deliveryAddress;
    }

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

    public String getContactPhoneNo() {
        return contactPhoneNo;
    }

    public void setContactPhoneNo(String contactPhoneNo) {
        this.contactPhoneNo = contactPhoneNo;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
