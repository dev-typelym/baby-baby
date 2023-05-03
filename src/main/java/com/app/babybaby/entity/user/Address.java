package com.app.babybaby.entity.user;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Address {
    private String address;
    private String addressDetail;
    private String addressSubDetail;
    private String postcode;
}
