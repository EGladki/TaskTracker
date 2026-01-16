package com.gladkiei.tasktracker.jwt.auth;

import com.gladkiei.tasktracker.dtos.ErrorResponse;
import com.gladkiei.tasktracker.mapper.ErrorResponseWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ErrorResponseWriter errorResponseWriter;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String errorMessage;
        if (authException instanceof BadCredentialsException) {
            errorMessage = "Bad credentials";
        } else if (authException instanceof UsernameNotFoundException) {
            errorMessage = "Username not found";
        } else  {
            log.warn(authException.getMessage());
            errorMessage = "Authentication error";
        }

        ErrorResponse error = new ErrorResponse(errorMessage);
        errorResponseWriter.writeJson(response, error, HttpServletResponse.SC_UNAUTHORIZED);
    }
}
