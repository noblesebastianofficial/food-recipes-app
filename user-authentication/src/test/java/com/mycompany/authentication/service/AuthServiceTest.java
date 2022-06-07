package com.mycompany.authentication.service;

import com.mycompany.authentication.model.User;
import com.mycompany.authentication.model.payload.RegistrationRequest;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthServiceTest {
    @Autowired
    AuthService authService;
    @MockBean
    UserService userService;
    @MockBean
    ApplicationEventPublisher applicationEventPublisher;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void TestRegusterdUser() {
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("sample@test.com");
        request.setPassword("pa55word");
        request.setUsername("samepleUser");

        User user = new User();
        long id = 1;
        user.setId(id);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        when(userService.createUser(request)).thenReturn(user);
        when(userService.save(user)).thenReturn(user);
        Optional<User> expectedUser = authService.registerUser(request);
        assertNotNull(expectedUser);

    }

    @Test
    public void TestRegisteredUserdDetails() {
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("sample@test.com");
        request.setPassword("pa55word");
        request.setUsername("samepleUser");
        request.setRegisterAsAdmin(false);

        User user = new User();
        long id = 1;
        user.setId(id);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        when(userService.createUser(request)).thenReturn(user);
        when(userService.save(user)).thenReturn(user);
        Optional<User> expectedUser = authService.registerUser(request);
        assertEquals("sample@test.com", expectedUser.get().getEmail());
        assertEquals("samepleUser", expectedUser.get().getUsername());
        assertEquals("pa55word", expectedUser.get().getPassword());

    }
}
