package com.gmail.vitaliapetsenak.shop.web.security;

import com.gmail.vitaliapetsenak.shop.repository.model.UserRole;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component("authenticationSuccessHandlerImpl")
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private static final Logger logger = Logger.getLogger(AuthenticationSuccessHandlerImpl.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            return;
        }
        logger.info("Authentication Success");
        try {
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;
        boolean isRoot = false;
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(UserRole.USER.name())) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals(UserRole.ADMIN.name())) {
                isAdmin = true;
                break;
            } else if (grantedAuthority.getAuthority().equals(UserRole.ROOT.name())) {
                isRoot = true;
                break;
            }
        }
        if (isUser) {
            return "/user";
        } else if (isAdmin) {
            return "/admin";
        } else if (isRoot) {
            return "/root";
        } else {
            throw new IllegalStateException();
        }
    }
}
