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

import com.sapientdemo.contacts.models.entities.PhoneNumber;
import com.sapientdemo.contacts.repositories.PhoneNumberRepository;
import com.sapientdemo.contacts.utils.GenericResponse;

public class PhoneNumberServiceTest {
    @Mock
    private PhoneNumberRepository phoneNumberRepository;

    @InjectMocks
    private PhoneNumberService phoneNumberService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        System.out.println("BEFORE EACH -> Phone Number Service Test");
    }

    private static List<PhoneNumber> phoneNumberList;

    @BeforeAll
    public static void beforeAll() {
        phoneNumberList = new ArrayList<>();

        phoneNumberList.add(PhoneNumber.builder()
                .id(1L)
                .number("1234567890")
                .description("Mobile")
                .build());

        phoneNumberList.add(PhoneNumber.builder()
                .id(2L)
                .number("9876543210")
                .description("Home")
                .build());
    }

    @Test
    public void testSavePhoneNumber() {
        // Arrange - Prepare test data
        PhoneNumber phoneNumber = PhoneNumber.builder()
                .id(1L)
                .number("1234567890")
                .description("Mobile")
                .build();

        when(phoneNumberRepository.save(phoneNumber)).thenReturn(phoneNumber);

        // Act - Call the service method
        phoneNumberService.savePhoneNumber(phoneNumber);

        // Assert - Verify the result
        verify(phoneNumberRepository, times(1)).save(phoneNumber);
    }

    @Test
    public void testFindPhoneNumberById() {
        // Arrange - Prepare test data
        Long phoneNumberId = 1L;
        PhoneNumber phoneNumber = phoneNumberList.get(0);
        when(phoneNumberRepository.findById(phoneNumberId)).thenReturn(Optional.of(phoneNumber));

        // Act - Call the service method
        PhoneNumber result = phoneNumberService.findPhoneNumberById(phoneNumberId);

        // Assert - Verify the result
        assertNotNull(result);
        assertEquals(phoneNumber, result);
        assertEquals("1234567890", result.getNumber());
        assertEquals("Mobile", result.getDescription());
    }

    @Test
    public void testFindPhoneNumberByIdNotFound() {
        // Arrange - Prepare test data
        Long phoneNumberId = 1L;
        when(phoneNumberRepository.findById(phoneNumberId)).thenReturn(Optional.empty());

        // Act - Call the service method
        PhoneNumber result = phoneNumberService.findPhoneNumberById(phoneNumberId);

        // Assert - Verify the result
        assertNull(result);
    }

    @Test
    public void testFindAllPhoneNumbers() {
        // Arrange - Prepare test data
        when(phoneNumberRepository.findAll()).thenReturn(phoneNumberList);

        // Act - Call the service method
        List<PhoneNumber> foundPhoneNumbers = phoneNumberService.findAllPhoneNumbers();

        // Assert - Verify the result
        assertNotNull(foundPhoneNumbers);
        assertEquals(2, foundPhoneNumbers.size());
        assertEquals("1234567890", foundPhoneNumbers.get(0).getNumber());
        assertEquals("Mobile", foundPhoneNumbers.get(0).getDescription());
        assertEquals("9876543210", foundPhoneNumbers.get(1).getNumber());
        assertEquals("Home", foundPhoneNumbers.get(1).getDescription());
    }

    @Test
    public void testDeletePhoneNumber() {
        // Arrange - Prepare test data
        PhoneNumber phoneNumber = phoneNumberList.get(0);
        when(phoneNumberRepository.findById(1L)).thenReturn(Optional.of(phoneNumber));
        doNothing().when(phoneNumberRepository).delete(phoneNumber);

        // Act - Call the service method
        GenericResponse response = phoneNumberService.deletePhoneNumber(1L);

        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals("Phone number deleted successfully", response.getMessage());

        verify(phoneNumberRepository, times(1)).delete(phoneNumber);
    }

    @Test
    public void testDeletePhoneNumberNotFound() {
        // Arrange - Prepare test data
        when(phoneNumberRepository.findById(1L)).thenReturn(Optional.empty());

        // Act - Call the service method
        GenericResponse response = phoneNumberService.deletePhoneNumber(1L);

        // Assert - Verify the result
        assertNotNull(response);
        assertFalse(response.isSuccess());
        assertEquals("Phone number not found", response.getMessage());
        verify(phoneNumberRepository, never()).delete(any());
    }
}
