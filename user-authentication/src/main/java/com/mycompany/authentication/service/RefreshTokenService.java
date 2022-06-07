package com.mycompany.authentication.service;

import com.mycompany.authentication.model.token.RefreshToken;

import java.util.Optional;
/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
public interface RefreshTokenService {

    /**
     * Find a refresh token based on the natural id i.e the token itself
     */
    public Optional<RefreshToken> findByToken(String token);

    /**
     * Persist the updated refreshToken instance to database
     */
    public RefreshToken save(RefreshToken refreshToken);

    /**
     * Creates and returns a new refresh token
     */
    public RefreshToken createRefreshToken() ;
    /**
     * Verify whether the token provided has expired or not on the basis of the current
     * server time and/or throw error otherwise
     */
    public void verifyExpiration(RefreshToken token) ;

    /**
     * Delete the refresh token associated with the user device
     */
    public void deleteById(Long id);

    /**
     * Increase the count of the token usage in the database. Useful for
     * audit purposes
     */
    public void increaseCount(RefreshToken refreshToken);
}
