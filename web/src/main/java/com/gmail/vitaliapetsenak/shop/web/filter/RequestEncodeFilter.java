package com.gmail.vitaliapetsenak.shop.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class RequestEncodeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
        resp.setContentType("text/html; charset=UTF-8");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
