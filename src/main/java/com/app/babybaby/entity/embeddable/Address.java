package com.app.babybaby.entity.embeddable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
@ToString
@RequiredArgsConstructor
public class Address {
    private String address;
    private String addressDetail;
    private String addressSubDetail;
    private String postcode;

    public Address(String address) {
        this.address = address;
    }
}
