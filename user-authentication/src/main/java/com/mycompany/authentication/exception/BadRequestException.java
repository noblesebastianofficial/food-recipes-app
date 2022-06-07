
package com.mycompany.authentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}