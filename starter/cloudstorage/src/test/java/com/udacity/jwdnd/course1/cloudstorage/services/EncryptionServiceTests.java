package com.udacity.jwdnd.course1.cloudstorage.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EncryptionServiceTests {

    @Autowired
    private EncryptionService encryptionService;

    @Test
    public void testEncryptAndDecryptValue()
    {
         String password = "root";
         String key = "PBKDF2WithHmacSH";

         String encrypted = this.encryptionService.encryptValue(password,key);
         assertNotNull(encrypted);
         assertNotEquals(encrypted, password);

         String decrypted = this.encryptionService.decryptValue(encrypted,key);
         assertNotNull(decrypted);
         assertEquals(decrypted,password);
    }

}
