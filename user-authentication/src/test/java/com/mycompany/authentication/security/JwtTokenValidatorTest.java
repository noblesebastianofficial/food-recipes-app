
package com.mycompany.authentication.security;

import com.mycompany.authentication.cache.LoggedOutJwtTokenCache;
import com.mycompany.authentication.event.OnUserLogoutSuccessEvent;
import com.mycompany.authentication.exception.InvalidTokenRequestException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;

/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
public class JwtTokenValidatorTest {

    private static final String jwtSecret = "testSecret";
    private static final long jwtExpiryInMs = 2500;
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Mock
    private LoggedOutJwtTokenCache loggedOutTokenCache;
    private JwtTokenProvider tokenProvider;
    private JwtTokenValidator tokenValidator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.tokenProvider = new JwtTokenProvider(jwtSecret, jwtExpiryInMs);
        this.tokenValidator = new JwtTokenValidator(jwtSecret, loggedOutTokenCache);
    }

    @Test
    public void testValidateTokenThrowsExceptionWhenTokenIsDamaged() {
        String token = tokenProvider.generateTokenFromUserId(100L);
        OnUserLogoutSuccessEvent logoutEvent = stubLogoutEvent("U1", token);
        when(loggedOutTokenCache.getLogoutEventForToken(token)).thenReturn(logoutEvent);

        thrown.expect(InvalidTokenRequestException.class);
        thrown.expectMessage("Incorrect signature");
        tokenValidator.validateToken(token + "-Damage");
    }

    @Test
    public void testValidateTokenThrowsExceptionWhenTokenIsExpired() throws InterruptedException {
        String token = tokenProvider.generateTokenFromUserId(123L);
        TimeUnit.MILLISECONDS.sleep(jwtExpiryInMs);
        OnUserLogoutSuccessEvent logoutEvent = stubLogoutEvent("U1", token);
        when(loggedOutTokenCache.getLogoutEventForToken(token)).thenReturn(logoutEvent);

        thrown.expect(InvalidTokenRequestException.class);
        thrown.expectMessage("Token expired. Refresh required");
        tokenValidator.validateToken(token);
    }

    @Test
    public void testValidateTokenThrowsExceptionWhenItIsPresentInTokenCache() {
        String token = tokenProvider.generateTokenFromUserId(124L);
        OnUserLogoutSuccessEvent logoutEvent = stubLogoutEvent("U2", token);
        when(loggedOutTokenCache.getLogoutEventForToken(token)).thenReturn(logoutEvent);

        thrown.expect(InvalidTokenRequestException.class);
        thrown.expectMessage("Token corresponds to an already logged out user [U2]");
        tokenValidator.validateToken(token);
    }

    @Test
    public void testValidateTokenWorksWhenItIsNotPresentInTokenCache() {
        String token = tokenProvider.generateTokenFromUserId(100L);
        tokenValidator.validateToken(token);
        verify(loggedOutTokenCache, times(1)).getLogoutEventForToken(token);
    }

    private OnUserLogoutSuccessEvent stubLogoutEvent(String email, String token) {
        return new OnUserLogoutSuccessEvent(email, token, null);
    }
}
