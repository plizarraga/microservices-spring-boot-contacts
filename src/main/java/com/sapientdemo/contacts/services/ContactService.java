package com.sapientdemo.contacts.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapientdemo.contacts.models.entities.Contact;
import com.sapientdemo.contacts.repositories.ContactRepository;
import com.sapientdemo.contacts.utils.GenericResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {

    private final ContactRepository contactRepository;

    public void saveContact(Contact contact) {
        log.info("Contact saved: {}", contact.getName());
        contactRepository.save(contact);
    }

    public List<Contact> findAllContacts() {
        log.info("Find all contacts");
        return contactRepository.findAll();
    }

    public Contact findContactById(Long contactId) {
        log.info("Find contact by id: {}", contactId);
        var contact = contactRepository.findById(contactId).orElse(null);
        return contact;
    }

    public GenericResponse deleteContact(Long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElse(null);

        if (contact == null) {
            log.info("Contact deleted by id not found: {}", contactId);
            return new GenericResponse(false, "Contact not found");
        }

        contactRepository.delete(contact);
        log.info("Contact deleted by id: {}", contactId);

        return new GenericResponse(true, "Contact deleted successfully");
    }
}
