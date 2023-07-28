package com.sapientdemo.contacts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapientdemo.contacts.models.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
