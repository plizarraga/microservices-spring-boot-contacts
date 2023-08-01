package com.sapientdemo.contacts.controllers;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sapientdemo.contacts.models.dtos.InputPhoneNumber;
import com.sapientdemo.contacts.models.entities.Contact;
import com.sapientdemo.contacts.models.entities.PhoneNumber;
import com.sapientdemo.contacts.services.ContactService;
import com.sapientdemo.contacts.services.PhoneNumberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PhoneNumberController {

    private final PhoneNumberService phoneNumberService;
    private final ContactService contactService;

    // Find all phone numbers
    @QueryMapping(name = "findAllPhoneNumbers")
    public List<PhoneNumber> findAll() {
        return phoneNumberService.findAllPhoneNumbers();
    }

    // Find phone number by id
    @QueryMapping(name = "findPhoneNumberById")
    public PhoneNumber findById(@Argument(name = "phoneNumberId") String id) {
        Long phoneNumberId = Long.parseLong(id);

        return phoneNumberService.findPhoneNumberById(phoneNumberId);
    }

    // Create phone number
    @MutationMapping
    public PhoneNumber createPhoneNumber(@Argument InputPhoneNumber inputPhoneNumber) {
        PhoneNumber phoneNumber = new PhoneNumber();

        phoneNumber.setNumber(inputPhoneNumber.getNumber());
        phoneNumber.setDescription(inputPhoneNumber.getDescription());

        // Set contact
        Long contactId = Long.parseLong(inputPhoneNumber.getContactId());
        Contact contact = contactService.findContactById(contactId);
        if (contact == null) {
            return null;
        }
        phoneNumber.setContact(contact);
        phoneNumberService.savePhoneNumber(phoneNumber);

        return phoneNumber;
    }

    // Delete phone number by id
    @MutationMapping
    public String deletePhoneNumber(@Argument(name = "phoneNumberId") String id) {
        Long phoneNumberId = Long.parseLong(id);

        return phoneNumberService.deletePhoneNumber(phoneNumberId).getMessage();
    }
}