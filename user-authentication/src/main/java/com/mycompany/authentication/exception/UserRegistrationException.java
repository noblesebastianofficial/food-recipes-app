
package com.mycompany.authentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class UserRegistrationException extends RuntimeException {

    private final String user;
    private final String message;

    public UserRegistrationException(String user, String message) {
        super(String.format("Failed to register User[%s] : '%s'", user, message));
        this.user = user;
        this.message = message;
    }

}