package com.night.backendWalkn.security;

import com.night.backendWalkn.configuration.AppProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TokenAuthFilter extends OncePerRequestFilter {
    /// Класс "Костыль", позволяющий использовать постоянный токен авторизации.
    // Сделано по причине того, что это API будет использоваться только мной, возможно я заменю это на нормальную авторизацию через jwt

    private final AppProperties appProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (!isSwaggerDock(request) &
                (Objects.isNull(token) || !token.equals(appProperties.getAuthToken()))) {
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

    private boolean isSwaggerDock(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.contains("swagger") | path.contains("v3");
    }
}