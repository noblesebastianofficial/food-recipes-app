package com.mycompany.authentication.util;
/**
 * * @author Noble Sebastian.
 *
 * @version 1.0.1.0
 */

import java.util.UUID;

public class Util {

    private Util() {
        throw new UnsupportedOperationException("Cannot instantiate a Util class");
    }

    public static String generateRandomUuid() {
        return UUID.randomUUID().toString();
    }
}
