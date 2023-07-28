package com.sapientdemo.contacts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapientdemo.contacts.models.entities.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {

}
