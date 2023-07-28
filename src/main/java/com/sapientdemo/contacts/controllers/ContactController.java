package com.sapientdemo.contacts.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sapientdemo.contacts.models.dtos.InputContact;
import com.sapientdemo.contacts.models.dtos.InputContactPhoneNumber;
import com.sapientdemo.contacts.models.entities.Contact;
import com.sapientdemo.contacts.models.entities.PhoneNumber;
import com.sapientdemo.contacts.services.ContactService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    // Find all contacts
    @QueryMapping(name = "findAllContacts")
    public List<Contact> findAll() {
        return contactService.findAllContacts();
    }

    // Find contact by id
    @QueryMapping(name = "findContactById")
    public Contact findById(@Argument(name = "contactId") String id) {
        Long contactId = Long.parseLong(id);

        return contactService.findContactById(contactId);
    }

    // Create contact
    @MutationMapping
    public Contact createContact(@Argument InputContact inputContact) {
        Contact contact = new Contact();
        contact.setName(inputContact.getName());
        contact.setEmail(inputContact.getEmail());
        contact.setAddress(inputContact.getAddress());

        // Map and set phone numbers for the contact
        if (inputContact.getPhoneNumbers() != null) {
            List<PhoneNumber> phoneNumbers = inputContact.getPhoneNumbers().stream()
                    .map(inputPhoneNumber -> mapInputContactPhoneNumberToPhoneNumber(inputPhoneNumber, contact))
                    .collect(Collectors.toList());
            contact.setPhoneNumbers(phoneNumbers);
        }

        contactService.saveContact(contact);

        return contact;
    }

    // Delete contact by id
    @MutationMapping
    public String deleteContact(@Argument(name = "contactId") String id) {
        Long contactId = Long.parseLong(id);

        return contactService.deleteContact(contactId).getMessage();
    }

    private PhoneNumber mapInputContactPhoneNumberToPhoneNumber(InputContactPhoneNumber inputPhoneNumber,
            Contact contact) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber(inputPhoneNumber.getNumber());
        phoneNumber.setDescription(inputPhoneNumber.getDescription());
        phoneNumber.setContact(contact);

        return phoneNumber;
    }

}