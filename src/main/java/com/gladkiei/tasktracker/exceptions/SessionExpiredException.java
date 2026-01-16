package com.gladkiei.tasktracker.exceptions;

import javax.security.sasl.AuthenticationException;

public class SessionExpiredException extends AuthenticationException {
    public SessionExpiredException(String message) {
        super(message);
    }
}
