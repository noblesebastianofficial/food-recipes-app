
package com.mycompany.authentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class UserLoginException extends RuntimeException {

    public UserLoginException(String message) {
        super(message);
    }

    public UserLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}