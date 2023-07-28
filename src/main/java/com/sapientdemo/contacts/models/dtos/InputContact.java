package com.sapientdemo.contacts.models.dtos;

import java.util.List;

import lombok.Data;

@Data
public class InputContact {
    private String name;
    private String email;
    private String address;
    private List<InputContactPhoneNumber> phoneNumbers;
}
