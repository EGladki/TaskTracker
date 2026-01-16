package com.gladkiei.tasktracker.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gladkiei.tasktracker.dtos.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class ErrorResponseWriter {

    private final ObjectMapper objectMapper;

    public void writeJson(HttpServletResponse response, ErrorResponse error, int status) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        objectMapper.writeValue(response.getWriter(), error);
    }
}
