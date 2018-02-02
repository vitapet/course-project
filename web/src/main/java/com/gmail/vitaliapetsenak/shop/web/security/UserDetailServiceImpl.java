package com.gmail.vitaliapetsenak.shop.web.security;

import com.gmail.vitaliapetsenak.shop.service.IUserService;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;
import com.gmail.vitaliapetsenak.shop.web.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDTO user = userService.getByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }
        return new UserPrincipal(user);
    }
}
