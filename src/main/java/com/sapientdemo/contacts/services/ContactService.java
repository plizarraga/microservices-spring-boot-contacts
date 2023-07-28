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
        contactRepository.save(contact);
        log.info("Contact added: {}", contact);
    }

    public List<Contact> findAllContacts() {
        return contactRepository.findAll();
    }

    public Contact findContactById(Long contactId) {
        var contact = contactRepository.findById(contactId).orElse(null);
        return contact;
    }

    public GenericResponse deleteContact(Long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElse(null);

        if (contact == null) {
            return new GenericResponse(false, "Contact not found");
        }

        contactRepository.delete(contact);
        log.info("Contact deleted: {}", contact);

        return new GenericResponse(true, "Contact deleted successfully");
    }
}
