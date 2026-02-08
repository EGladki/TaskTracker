package com.gladkiei.tasktracker.auth.internal;

import com.gladkiei.tasktracker.dtos.ErrorResponse;
import com.gladkiei.tasktracker.mapper.ErrorResponseWriter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class InternalFilter extends OncePerRequestFilter {

    private final ErrorResponseWriter errorResponseWriter;

    @Value("${internal.api.key}")
    private String apiKey;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getRequestURI().startsWith("/internal")) {
            String header = request.getHeader("INTERNAL_API_KEY");

            if (!apiKey.equals(header)) {

                ErrorResponse error = new ErrorResponse("Unauthorized internal service");
                errorResponseWriter.writeJson(response, error, HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
