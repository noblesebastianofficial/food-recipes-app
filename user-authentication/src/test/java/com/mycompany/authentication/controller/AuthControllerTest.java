package com.mycompany.authentication.controller;

import com.mycompany.authentication.event.OnUserRegistrationCompleteEvent;
import com.mycompany.authentication.model.User;
import com.mycompany.authentication.model.payload.RegistrationRequest;
import com.mycompany.authentication.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthControllerTest {

    @Autowired
    AuthController authController;
    @MockBean
    UserService userService;
    @MockBean
    ApplicationEventPublisher applicationEventPublisher;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void signupTestStatusAdminUserOK() {
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("sample@test.com");
        request.setPassword("pa55word");
        request.setUsername("samepleUser");
        request.setRegisterAsAdmin(true);

        User user=new User();
        long id=1;
        user.setId(id);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        UriComponentsBuilder urlBuilder = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth/registrationConfirmation");
        OnUserRegistrationCompleteEvent onUserRegistrationCompleteEvent = new OnUserRegistrationCompleteEvent(user, urlBuilder);
        //when(applicationEventPublisher.publishEvent(onUserRegistrationCompleteEvent));
        Mockito.doNothing().when(applicationEventPublisher).publishEvent(onUserRegistrationCompleteEvent);
        when(userService.createUser(request)).thenReturn(user);
        when(userService.save(user)).thenReturn(user);
        ResponseEntity expectedUse= authController.registerUser(request);
        assertEquals(HttpStatus.OK,expectedUse.getStatusCode());

    }
    @Test
    public void signupTestStatusNormalUserOK() {
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("sample@test.com");
        request.setPassword("pa55word");
        request.setUsername("samepleUser");
        request.setRegisterAsAdmin(false);

        User user=new User();
        long id=1;
        user.setId(id);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        UriComponentsBuilder urlBuilder = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth/registrationConfirmation");
        OnUserRegistrationCompleteEvent onUserRegistrationCompleteEvent = new OnUserRegistrationCompleteEvent(user, urlBuilder);
        //when(applicationEventPublisher.publishEvent(onUserRegistrationCompleteEvent));
        Mockito.doNothing().when(applicationEventPublisher).publishEvent(onUserRegistrationCompleteEvent);
        when(userService.createUser(request)).thenReturn(user);
        when(userService.save(user)).thenReturn(user);
        ResponseEntity expectedUse= authController.registerUser(request);
        assertEquals(HttpStatus.OK,expectedUse.getStatusCode());

    }
}
