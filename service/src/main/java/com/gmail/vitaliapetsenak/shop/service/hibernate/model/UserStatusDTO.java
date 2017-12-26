package com.gmail.vitaliapetsenak.shop.service.hibernate.model;


import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.UserStatus;

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
