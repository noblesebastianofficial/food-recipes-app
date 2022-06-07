package com.mycompany.authentication.service;

import com.mycompany.authentication.annotation.CurrentUser;
import com.mycompany.authentication.model.CustomUserDetails;
import com.mycompany.authentication.model.User;
import com.mycompany.authentication.model.payload.LogOutRequest;
import com.mycompany.authentication.model.payload.RegistrationRequest;
/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
public interface UserService {

    public User save(User user);

    /**
     * Check is the user exists given the email: naturalId
     */
    public Boolean existsByEmail(String email);

    /**
     * Check is the user exists given the username: naturalId
     */
    public Boolean existsByUsername(String username);


    /**
     * Creates a new user from the registration request
     */
    public User createUser(RegistrationRequest registerRequest);



    /**
     * Log the given user out and delete the refresh token associated with it. If no device
     * id is found matching the database for the given user, throw a log out exception.
     */
    public void logoutUser(@CurrentUser CustomUserDetails currentUser, LogOutRequest logOutRequest);
}
