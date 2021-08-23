package com.udacity.jwdnd.course1.cloudstorage.services;
import com.udacity.jwdnd.course1.cloudstorage.viewmodels.CredentialForm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialServiceTests {

    @Autowired
    private CredentialService credentialService;

    private static CredentialForm frm;

    @BeforeAll
    public static void initForm()
    {
        frm = new CredentialForm();
        frm.setUrl("https://localhost");
        frm.setPassword("itnoa123");
        frm.setUsername("omar");
        frm.setUserId(1);
    }
    @BeforeEach
    public void setup()
    {
        Integer id = this.credentialService.addCredential(frm);
        frm.setCredentialid(id);
        System.out.println("cred id = " + frm.getCredentialid());

    }

    @AfterEach
    public void cleanup()
    {
        this.credentialService.deleteCredential(frm.getCredentialid(),frm.getUserId());
    }
    @Test
    public void testGetAllCredentials()
    {
        var items = this.credentialService.getAllCredentials(frm.getUserId());
        assertNotNull(items);
        assertNotEquals(0,items.size());

    }

    @Test
    public void testUpdateCredential()
    {
        frm.setUrl("https://update.url");
        var found = this.credentialService.updateCredential(frm);
        assertTrue(found > 0);
    }





}
