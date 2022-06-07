
package com.mycompany.authentication.model.payload;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.Instant;
/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse implements Serializable {

    private final String message;
    private final Boolean success;
    private final String timestamp;
    private final String cause;
    private final String path;

    public ApiResponse(Boolean success, String data, String cause, String path) {
        this.timestamp = Instant.now().toString();
        this.message = data;
        this.success = success;
        this.cause = cause;
        this.path = path;
    }

    public ApiResponse(Boolean success, String data) {
        this.timestamp = Instant.now().toString();
        this.message = data;
        this.success = success;
        this.cause = null;
        this.path = null;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getCause() {
        return cause;
    }

    public String getPath() {
        return path;
    }
}
