package com.gmail.vitaliapetsenak.shop.service.hibernate.model;


import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.UserRole;

public enum RoleDTO {
    ROOT(UserRole.ROOT),
    ADMIN(UserRole.ADMIN),
    USER(UserRole.USER);

    private UserRole role;

    RoleDTO(UserRole role) {
        this.role = role;
    }

    public UserRole getRole() {
        return role;
    }
}
