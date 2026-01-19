package com.gladkiei.tasktracker.handlers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.gladkiei.tasktracker.dtos.ErrorResponse;
import com.gladkiei.tasktracker.exceptions.EmailAlreadyExistException;
import com.gladkiei.tasktracker.exceptions.InternalServerException;
import com.gladkiei.tasktracker.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorResponse handleAlreadyExistException(EmailAlreadyExistException ex) {
        log.warn(ex.getMessage());
        return new ErrorResponse("This email is already taken");
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        String message = "Invalid request body";

        if (ex.getCause() instanceof InvalidFormatException ife) {
            message = "Invalid value for field '" +
                    ife.getPath().get(0).getFieldName() + "'";
        }

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(message));

    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleNotFound(NotFoundException ex) {
        log.warn(ex.getMessage());
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleInternalServer(InternalServerException ex) {
        log.warn(ex.getMessage());
        return new ErrorResponse(ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        log.warn(ex.getMessage());
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(message));
    }

}
