package com.app.babybaby.entity.embeddable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter @Setter
@ToString
@RequiredArgsConstructor
public class Address implements Serializable {
    private String address;
    private String addressDetail;
    private String addressSubDetail;
    private String postcode;

    public Address(String address) {
        this.address = address;
    }
}
