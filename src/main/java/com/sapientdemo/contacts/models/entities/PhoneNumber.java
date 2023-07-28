package com.sapientdemo.contacts.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "phone_numbers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String number;
    private String description;

    // Many-to-One relationship with Contact entity
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Contact.class)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;
}