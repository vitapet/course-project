package com.gmail.vitaliapetsenak.shop.repository.model;

public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN"),
    ROOT("ROOT");

    private String role;

    UserRole(String role) {
        this.role = role;
    }
}
