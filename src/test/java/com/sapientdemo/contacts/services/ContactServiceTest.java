package com.sapientdemo.contacts.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sapientdemo.contacts.models.entities.Contact;
import com.sapientdemo.contacts.repositories.ContactRepository;
import com.sapientdemo.contacts.utils.GenericResponse;

public class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    private static List<Contact> contactList;

    @BeforeAll
    public static void beforeAll() {
        contactList = new ArrayList<>();
        contactList.add(Contact.builder()
                .id(1L)
                .name("John Doe")
                .email("john@example.com")
                .build());

        contactList.add(Contact.builder()
                .id(2L)
                .name("Jane Doe")
                .email("jane@example.com")
                .build());
    }

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveContact() {
        // Arrange - Prepare test data
        Contact contact = Contact.builder()
                .id(3L)
                .name("Alisa Doe")
                .email("alisa.doe@example.com")
                .build();

        when(contactRepository.save(contact)).thenReturn(contact);

        // Act - Call the service method
        contactService.saveContact(contact);

        // Assert - Verify the result
        verify(contactRepository, times(1)).save(contact);
    }

    @Test
    public void testFindContactById() {
        // Arrange - Prepare test data
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contactList.get(0)));

        // Act - Call the service method
        Contact foundContact = contactService.findContactById(1L);

        // Assert - Verify the result
        assertNotNull(foundContact);
        assertEquals("John Doe", foundContact.getName());
        assertEquals("john@example.com", foundContact.getEmail());
    }

    @Test
    public void testFindContactByIdNotFound() {
        // Arrange - Prepare test data
        when(contactRepository.findById(1L)).thenReturn(Optional.empty());

        // Act - Call the service method
        Contact foundContact = contactService.findContactById(1L);
        
        // Assert - Verify the result
        assertNull(foundContact);
    }

    @Test
    public void testFindAllContacts() {
        // Arrange - Prepare test data
        when(contactRepository.findAll()).thenReturn(contactList);

        // Act - Call the service method
        List<Contact> foundContacts = contactService.findAllContacts();

        // Assert - Verify the result
        assertNotNull(foundContacts);
        assertEquals(2, foundContacts.size());
        assertEquals("John Doe", foundContacts.get(0).getName());
        assertEquals("john@example.com", foundContacts.get(0).getEmail());
        assertEquals("Jane Doe", foundContacts.get(1).getName());
        assertEquals("jane@example.com", foundContacts.get(1).getEmail());
    }

    @Test
    public void testDeleteContact() {
        // Arrange - Prepare test data
        Contact contact = contactList.get(0);

        // Act - Call the service method
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));
        doNothing().when(contactRepository).delete(contact);
        GenericResponse response = contactService.deleteContact(1L);

        // Assert - Verify the result
        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals("Contact deleted successfully", response.getMessage());

        verify(contactRepository, times(1)).delete(contact);
    }

    @Test
    public void testDeleteContactNotFound() {
        // Arrange - Prepare test data
        when(contactRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act - Call the service method
        GenericResponse response = contactService.deleteContact(1L);

        // Assert - Verify the result
        assertNotNull(response);
        assertFalse(response.isSuccess());
        assertEquals("Contact not found", response.getMessage());

        verify(contactRepository, never()).delete(any());
    }
}