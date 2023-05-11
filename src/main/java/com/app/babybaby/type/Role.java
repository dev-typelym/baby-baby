package com.app.babybaby.type;

public enum Role {
    GENERAL, ADMIN, COMPANY;

    private static final String ROLE_PREFIX = "ROLE_";

    public String getSecurityRole(){
        return ROLE_PREFIX + name();
    }
}
