package com.gladkiei.tasktracker.jwt.auth;

import com.gladkiei.tasktracker.dtos.ErrorResponse;
import com.gladkiei.tasktracker.mapper.ErrorResponseWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ErrorResponseWriter errorResponseWriter;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ErrorResponse error = new ErrorResponse("Session expired or invalid token");
        errorResponseWriter.writeJson(response, error, HttpServletResponse.SC_FORBIDDEN);
    }
}
