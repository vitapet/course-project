package com.gmail.vitaliapetsenak.shop.web.model;

import com.gmail.vitaliapetsenak.shop.repository.model.UserRole;
import com.gmail.vitaliapetsenak.shop.repository.model.UserStatus;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {


    private UserDTO user;
    private Collection<SimpleGrantedAuthority> grantedAuthorities;

    public UserPrincipal(UserDTO user) {
        this.user = user;
        grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
    }

    public Long getUserId() {
        return user.getId();
    }

    public UserRole getRole() {
        return user.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !(user.getStatus().equals(UserStatus.BLOCKED)
                || user.getStatus().equals(UserStatus.DELETED));
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus().equals(UserStatus.ACTIVE);
    }
}
