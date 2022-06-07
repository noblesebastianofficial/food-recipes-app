package com.mycompany.authentication.service;


import com.mycompany.authentication.model.CustomUserDetails;
import com.mycompany.authentication.model.User;
import com.mycompany.authentication.model.payload.LoginRequest;
import com.mycompany.authentication.model.payload.RegistrationRequest;
import com.mycompany.authentication.model.token.RefreshToken;
import org.springframework.security.core.Authentication;

import java.util.Optional;
/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
public interface AuthService {


    /**
     * Registers a new user in the database by performing a series of quick checks.
     *
     * @return A user object if successfully created
     */
    Optional<User> registerUser(RegistrationRequest newRegistrationRequest);

    /**
     * Checks if the given email already exists in the database repository or not
     *
     * @return true if the email exists else false
     */
    Boolean emailAlreadyExists(String email);

    /**
     * Authenticate user and log them in given a loginRequest
     */
    Optional<Authentication> authenticateUser(LoginRequest loginRequest);


    /**
     * Generates a JWT token for the validated client
     */
    String generateToken(CustomUserDetails customUserDetails);

    /**
     * Creates and persists the refresh token for the user device. If device exists
     * already, we don't care. Unused devices with expired tokens should be cleaned
     * with a cron job. The generated token would be encapsulated within the jwt.
     * Remove the existing refresh token as the old one should not remain valid.
     */
    public Optional<RefreshToken> createAndPersistRefreshTokenForDevice(Authentication authentication, LoginRequest loginRequest);


}
