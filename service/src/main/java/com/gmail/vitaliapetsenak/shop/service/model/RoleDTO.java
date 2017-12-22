package com.gmail.vitaliapetsenak.shop.service.model;

import com.gmail.vitaliapetsenak.shop.repository.model.Role;

public enum RoleDTO {
    ROOT(Role.ROOT),
    ADMIN(Role.ADMIN),
    USER(Role.USER);

    private Role role;

    RoleDTO(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}
