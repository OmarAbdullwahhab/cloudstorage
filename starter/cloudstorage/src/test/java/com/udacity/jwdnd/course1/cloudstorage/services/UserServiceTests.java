package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.viewmodels.SignupForm;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTests {


    @Autowired
    private UserService userService;

    private static SignupForm frm;


    @BeforeAll
    public static void fillForm() {
        frm = new SignupForm();
        frm.setFirstName("Test");
        frm.setLastName("Tester");
        frm.setUsername("app1");
        frm.setPassword("app1");

    }

    @BeforeEach
    public void setupTest() {
        this.userService.createUser(frm);
        var foundUser = this.userService.getUser(frm.getUsername());
        frm.setUserid(foundUser.getUserid());

    }

    @AfterEach
    public void cleanUp() {
        this.userService.deleteUser(frm.getUsername());
    }

    @Test
    public void testIsUsernameAvailable() {
        boolean available = this.userService.isUsernameAvailable(frm.getUsername());
        assertFalse(available);
    }


    @Test
    public void testGetUserById() {
        var user = this.userService.getUser(frm.getUserid());
        assertEquals(frm.getFirstName(), user.getFirstName());

    }

    @Test
    public void testGetUserByName() {
        var user = this.userService.getUser(frm.getUsername());
        assertEquals(frm.getFirstName(), user.getFirstName());

    }


}
