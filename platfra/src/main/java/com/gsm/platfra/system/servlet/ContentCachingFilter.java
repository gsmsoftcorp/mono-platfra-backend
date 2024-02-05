package com.gsm.platfra.system.servlet;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

/**
 *
 */
public class ContentCachingFilter extends OncePerRequestFilter {
    private final Set<String> ignoreUris;
    private final PathMatcher pathMatcher;

    public ContentCachingFilter(Set<String> ignoreUris) {
        this.ignoreUris = ignoreUris;
        this.pathMatcher = new AntPathMatcher();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (ignore(request.getRequestURI())) {
            chain.doFilter(request, response);
        } else {
            CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(request);
            chain.doFilter(cachedBodyHttpServletRequest, response);
        }
    }

    private boolean ignore(String requestUri) {
        for (String ignoreUri : ignoreUris) {
            if (pathMatcher.match(ignoreUri, requestUri)) {
                return true;
            }
        }
        return false;
    }
}
