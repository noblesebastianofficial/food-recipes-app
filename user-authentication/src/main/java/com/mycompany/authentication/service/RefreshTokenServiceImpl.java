
package com.mycompany.authentication.service;

import com.mycompany.authentication.exception.TokenRefreshException;
import com.mycompany.authentication.model.token.RefreshToken;
import com.mycompany.authentication.repository.RefreshTokenRepository;
import com.mycompany.authentication.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService{
    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Value("${app.tokenrefreshduration}")
    private Long refreshTokenDurationMs;


    /**
     * Find a refresh token based on the natural id i.e the token itself
     */
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    /**
     * Persist the updated refreshToken instance to database
     */
    public RefreshToken save(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    /**
     * Creates and returns a new refresh token
     */
    public RefreshToken createRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(Util.generateRandomUuid());
        refreshToken.setRefreshCount(0L);
        return refreshToken;
    }

    /**
     * Verify whether the token provided has expired or not on the basis of the current
     * server time and/or throw error otherwise
     */
    public void verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            throw new TokenRefreshException(token.getToken(), "Expired token. Please issue a new request");
        }
    }

    /**
     * Delete the refresh token associated with the user device
     */
    public void deleteById(Long id) {
        refreshTokenRepository.deleteById(id);
    }

    /**
     * Increase the count of the token usage in the database. Useful for
     * audit purposes
     */
    public void increaseCount(RefreshToken refreshToken) {
        refreshToken.incrementRefreshCount();
        save(refreshToken);
    }
}
