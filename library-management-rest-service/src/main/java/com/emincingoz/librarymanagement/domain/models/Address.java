package com.emincingoz.librarymanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "lms_address")
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "street_address")
    private String streetAddress;
    private String mahalle;
    private String city;
    @Column(name = "zip_code")
    private String zipCode;
    private String country;
}
