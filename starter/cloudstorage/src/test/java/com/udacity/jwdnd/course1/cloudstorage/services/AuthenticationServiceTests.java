package com.udacity.jwdnd.course1.cloudstorage.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationServiceTests {

    @Autowired
    private AuthenticationService authenticationService;


    @Test
    public void testAuthenticate()
    {
        SecurityContextHolder.setContext(new SecurityContextImpl());
        Authentication auth = new UsernamePasswordAuthenticationToken("admin","admin");
        var test = this.authenticationService.authenticate(auth);
        assertNotNull(test);
    }

    @Test
    public void testSupports()
    {
        var wrong = this.authenticationService.supports(AnonymousAuthenticationToken.class);
        var right = this.authenticationService.supports(UsernamePasswordAuthenticationToken.class);
        assertTrue(right);
        assertFalse(wrong);
    }


}
