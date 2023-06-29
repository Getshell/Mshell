package com.memshell.generic;

import javax.servlet.*;
import java.io.IOException;

public class BasicFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("[+]Basic Filter invoked...");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
