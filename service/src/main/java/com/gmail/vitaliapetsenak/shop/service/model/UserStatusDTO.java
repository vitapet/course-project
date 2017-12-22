package com.gmail.vitaliapetsenak.shop.service.model;

import com.gmail.vitaliapetsenak.shop.repository.model.UserStatus;

public enum UserStatusDTO {
    ACTIVE(UserStatus.ACTIVE),
    DELETED(UserStatus.DELETED),
    BLOCKED(UserStatus.BLOCKED);

    private UserStatus status;

    UserStatusDTO(UserStatus status) {
        this.status = status;
    }

    public UserStatus getStatus() {
        return status;
    }
}
