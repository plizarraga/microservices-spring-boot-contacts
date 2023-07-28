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
        phoneNumberRepository.save(phoneNumber);
        log.info("Phone number added: {}", phoneNumber);
    }

    public List<PhoneNumber> findAllPhoneNumbers() {
        return phoneNumberRepository.findAll();
    }

    public PhoneNumber findPhoneNumberById(Long phoneNumberId) {
        var phoneNumber = phoneNumberRepository.findById(phoneNumberId).orElse(null);
        return phoneNumber;
    }

    public GenericResponse deletePhoneNumber(Long phoneNumberId) {
        PhoneNumber phoneNumber = phoneNumberRepository.findById(phoneNumberId)
                .orElse(null);

        if (phoneNumber == null) {
            return new GenericResponse(false, "Phone number not found");
        }

        phoneNumberRepository.delete(phoneNumber);
        log.info("Phone number deleted: {}", phoneNumber);

        return new GenericResponse(true, "Phone number deleted successfully");
    }
}
