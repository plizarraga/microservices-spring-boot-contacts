package com.sapientdemo.contacts.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapientdemo.contacts.models.entities.PhoneNumber;
import com.sapientdemo.contacts.repositories.PhoneNumberRepository;
import com.sapientdemo.contacts.utils.GenericResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhoneNumberService {

    private final PhoneNumberRepository phoneNumberRepository;

    public void savePhoneNumber(PhoneNumber phoneNumber) {
        log.info("Phone number saved: {}", phoneNumber.getNumber());
        phoneNumberRepository.save(phoneNumber);
    }

    public List<PhoneNumber> findAllPhoneNumbers() {
        log.info("Find all phone numbers");
        return phoneNumberRepository.findAll();
    }

    public PhoneNumber findPhoneNumberById(Long phoneNumberId) {
        log.info("Find phone number by id: {}", phoneNumberId);
        var phoneNumber = phoneNumberRepository.findById(phoneNumberId).orElse(null);
        return phoneNumber;
    }

    public GenericResponse deletePhoneNumber(Long phoneNumberId) {
        PhoneNumber phoneNumber = phoneNumberRepository.findById(phoneNumberId)
                .orElse(null);

        if (phoneNumber == null) {
            log.info("Phone number deleted by id not found: {}", phoneNumberId);
            return new GenericResponse(false, "Phone number not found");
        }

        phoneNumberRepository.delete(phoneNumber);
        log.info("Phone number deleted by id: {}", phoneNumberId);

        return new GenericResponse(true, "Phone number deleted successfully");
    }
}
