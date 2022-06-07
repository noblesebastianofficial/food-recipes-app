
package com.mycompany.authentication.security;

import com.mycompany.authentication.model.CustomUserDetails;
import com.mycompany.authentication.model.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
public class JwtTokenProviderTest {

    private static final String jwtSecret = "testSecret";
    private static final long jwtExpiryInMs = 2500;
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private JwtTokenProvider tokenProvider;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.tokenProvider = new JwtTokenProvider(jwtSecret, jwtExpiryInMs);
    }

    @Test
    public void testGetUserIdFromJWT() {
        String token = tokenProvider.generateToken(stubCustomUser());
        assertEquals(100, tokenProvider.getUserIdFromJWT(token).longValue());
    }

    @Test
    public void testGetTokenExpiryFromJWT() {
        String token = tokenProvider.generateTokenFromUserId(120L);
        assertNotNull(tokenProvider.getTokenExpiryFromJWT(token));
    }

    @Test
    public void testGetExpiryDuration() {
        assertEquals(jwtExpiryInMs, tokenProvider.getExpiryDuration());
    }

    private CustomUserDetails stubCustomUser() {
        User user = new User();
        user.setId((long) 100);
        return new CustomUserDetails(user);
    }
}
