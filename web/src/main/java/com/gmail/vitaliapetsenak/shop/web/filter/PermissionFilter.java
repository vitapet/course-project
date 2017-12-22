package com.gmail.vitaliapetsenak.shop.web.filter;


import com.gmail.vitaliapetsenak.shop.service.model.RoleDTO;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PermissionFilter implements Filter {
    private static final String LOGIN_URL = "/login";

    @Override
    public void init(FilterConfig filterConfig) {
        //Default instantiate
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        UserDTO user = (UserDTO) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + LOGIN_URL);
        } else {
            String requestURL = req.getRequestURL().toString();
            if (user.getRole() == RoleDTO.USER) {
                if (requestURL.contains("/user")) {
                    chain.doFilter(request, response);
                } else {
                    req.getSession().setAttribute("user", null);
                    resp.sendRedirect(req.getContextPath() + LOGIN_URL);
                }
            } else if (user.getRole() == RoleDTO.ADMIN || user.getRole() == RoleDTO.ROOT) {
                if (requestURL.contains("/admin")) {
                    chain.doFilter(request, response);
                } else {
                    req.getSession().setAttribute("user", null);
                    resp.sendRedirect(req.getContextPath() + LOGIN_URL);
                }
            }
        }
    }

    @Override
    public void destroy() {
        //Default instantiate
    }

}
