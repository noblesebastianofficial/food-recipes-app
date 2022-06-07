package com.mycompany.authentication.controller;

import com.mycompany.authentication.event.OnUserRegistrationCompleteEvent;
import com.mycompany.authentication.exception.UserLoginException;
import com.mycompany.authentication.exception.UserRegistrationException;
import com.mycompany.authentication.model.CustomUserDetails;
import com.mycompany.authentication.model.payload.ApiResponse;
import com.mycompany.authentication.model.payload.JwtAuthenticationResponse;
import com.mycompany.authentication.model.payload.LoginRequest;
import com.mycompany.authentication.model.payload.RegistrationRequest;
import com.mycompany.authentication.model.token.RefreshToken;
import com.mycompany.authentication.security.JwtTokenProvider;
import com.mycompany.authentication.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * *@author Noble Sebastian.
 * @version 1.0.1.0
 */

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Api(value = "Authorization Rest API")
public class AuthController {

    private static final Logger logger = Logger.getLogger(AuthController.class);
    @Autowired
    AuthService authService;
    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    /**
     * Entry point for the user log in. Return the jwt auth token and the refresh token
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Logs the user in to the system and return the auth tokens")
    public ResponseEntity authenticateUser(
        @ApiParam(value = "The LoginRequest payload") @Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authService.authenticateUser(loginRequest)
            .orElseThrow(() -> new UserLoginException("Couldn't login user [" + loginRequest + "]"));

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        logger.info("Logged in User returned [API]: " + customUserDetails.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authService.createAndPersistRefreshTokenForDevice(authentication, loginRequest)
            .map(RefreshToken::getToken)
            .map(refreshToken -> {
                String jwtToken = authService.generateToken(customUserDetails);
                return ResponseEntity
                    .ok(new JwtAuthenticationResponse(jwtToken, refreshToken, tokenProvider.getExpiryDuration(),
                        customUserDetails.getId(), customUserDetails.getUsername(), customUserDetails.getEmail()));
            })
            .orElseThrow(() -> new UserLoginException("Couldn't create refresh token for: [" + loginRequest + "]"));
    }

    /**
     * Entry point for the user registration process. On successful registration, publish an event to generate email
     * verification token
     */
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registers the user and publishes an event to generate the email verification")
    public ResponseEntity registerUser(
        @ApiParam(value = "The RegistrationRequest payload") @Valid @RequestBody RegistrationRequest registrationRequest) {

        return authService.registerUser(registrationRequest)
            .map(user -> {
                UriComponentsBuilder urlBuilder = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/auth/registrationConfirmation");
                OnUserRegistrationCompleteEvent onUserRegistrationCompleteEvent = new OnUserRegistrationCompleteEvent(
                    user, urlBuilder);
                applicationEventPublisher.publishEvent(onUserRegistrationCompleteEvent);
                logger.info("Registered User returned [API[: " + user);
                return ResponseEntity
                    .ok(new ApiResponse(true, "User registered successfully. Check your email for verification"));
            })
            .orElseThrow(
                () -> new UserRegistrationException(registrationRequest.getEmail(), "Missing user object in database"));
    }


}
