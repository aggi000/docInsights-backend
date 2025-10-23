package com.example.docInsights.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@Order(0) // run early
public class InternalKeyFilter extends OncePerRequestFilter {

    @Value("${app.internal-key:}")
    private String internalKey; // empty if not set

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        String method = request.getMethod();

        if (path.startsWith("/actuator")) return true;
        if (path.startsWith("/h2-console")) return true;

        if (HttpMethod.GET.matches(method) && path.matches("^/api/documents/\\d+/extractions/latest$")) {
            return true;
        }

        // for all posts
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        // If no key configured, allow all (dev convenience) — you can flip this to block instead.
//        if (internalKey == null || internalKey.isBlank()) {
//            System.out.println("No internal key provided");
//            chain.doFilter(req, res);
//            return;
//        }

        System.out.println("Internal key loaded? " + !internalKey.isBlank());

        String provided = req.getHeader("X-Internal-Key");
        if (provided == null || !provided.equals(internalKey)) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
            res.setContentType("application/json");
            res.getWriter().write("{\"error\":\"unauthorized\"}");
            return;
        }

        chain.doFilter(req, res);
    }
}
