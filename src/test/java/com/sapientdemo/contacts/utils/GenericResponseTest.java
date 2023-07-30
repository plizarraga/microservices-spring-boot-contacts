package com.sapientdemo.contacts.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GenericResponseTest {

    @Test
    void testConstructorAndGetters() {
        // Create a GenericResponse object with success=true and message="Test Success"
        GenericResponse response = new GenericResponse(true, "Test Success");

        // Use the getters to verify the values
        assertEquals(true, response.isSuccess());
        assertEquals("Test Success", response.getMessage());
    }

    @Test
    void testConstructorAndGettersWithFailure() {
        // Create a GenericResponse object with success=false and message="Test Failure"
        GenericResponse response = new GenericResponse(false, "Test Failure");

        // Use the getters to verify the values
        assertEquals(false, response.isSuccess());
        assertEquals("Test Failure", response.getMessage());
    }
}
