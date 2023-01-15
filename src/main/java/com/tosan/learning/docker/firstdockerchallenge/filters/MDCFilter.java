package com.tosan.learning.docker.firstdockerchallenge.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class MDCFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        var fullRequestUrl = StringUtils.isNoneEmpty(req.getQueryString()) ? req.getRequestURI() + "?" + req.getQueryString() : req.getRequestURI();
        MDC.put("request", fullRequestUrl);
        MDC.put("status", "200");
        try {
            filterChain.doFilter(req, res);
        } finally {
            MDC.clear();
        }
    }
}
