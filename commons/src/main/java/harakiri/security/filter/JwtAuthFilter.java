package harakiri.security.filter;


import harakiri.config.Constants;
import harakiri.security.jwt.JwtUtils;
import harakiri.security.jwt.TokenType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(Constants.authHeaderName);
        if (authHeader == null || authHeader.equals("null")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!jwtUtils.validateJwtToken(authHeader, TokenType.ACCESS)) {
            filterChain.doFilter(request, response);
            return;
        }
        long userId = jwtUtils.getIdFromJwtToken(authHeader);

        UserContextHolder.setId(userId);

        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(userId, null, Collections.emptyList());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
