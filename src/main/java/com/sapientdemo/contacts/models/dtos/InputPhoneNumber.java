package com.sapientdemo.contacts.models.dtos;

import lombok.Data;

@Data
public class InputPhoneNumber {
    private String number;
    private String description;
    private String contactId;
}
