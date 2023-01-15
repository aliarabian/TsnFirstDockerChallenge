package com.tosan.learning.docker.firstdockerchallenge.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class MDCFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        MDC.put("request", request.getRequestURI() + "?" + request.getQueryString());
        MDC.put("status", "200");
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
