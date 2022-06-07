
package com.mycompany.authentication.model;
/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
public enum TokenStatus {
    /**
     * Token is in pending state awaiting user confirmation
     */
    STATUS_PENDING,

    /**
     * Token has been confirmed successfully by the user
     */
    STATUS_CONFIRMED
}
