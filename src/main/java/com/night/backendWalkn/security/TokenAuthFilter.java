package com.night.backendWalkn.security;

import com.night.backendWalkn.configuration.AppProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class TokenAuthFilter extends OncePerRequestFilter {
    /// Класс "Костыль", позволяющий использовать постоянный токен авторизации.
    // Сделано по причине того, что это API будет использоваться только мной, возможно я заменю это на нормальную авторизацию через jwt

    private final AppProperties appProperties;

    public TokenAuthFilter(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if (!token.equals(appProperties.getAuthToken())) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        // Всё ок — ставим "анонимную" авторизацию
        Authentication auth = new UsernamePasswordAuthenticationToken(
                "internal-user", null, List.of());
        SecurityContextHolder
                .getContext()
                .setAuthentication(auth);

        filterChain.doFilter(request, response);
    }
}