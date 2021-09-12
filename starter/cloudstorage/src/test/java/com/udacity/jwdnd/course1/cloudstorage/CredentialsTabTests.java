package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.viewmodels.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.viewmodels.SignupForm;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialsTabTests {

    @LocalServerPort
    private int port;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private UserService userService;

    private WebDriver driver;
    private SignupForm frm;

    private String username = "testuser";
    private String password = "testuser";

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();

        if(this.userService.isUsernameAvailable(this.username)) {
            frm = new SignupForm();
            frm.setUsername(this.username);
            frm.setPassword(this.password);
            frm.setFirstName("System");
            frm.setLastName("Tester");
            this.userService.createUser(frm);

        }
        else
        {
            var user = this.userService.getUser(this.username);
            frm = new SignupForm();
            frm.setUserid(user.getUserid());
            frm.setUsername(user.getUsername());
            frm.setFirstName(user.getFirstName());
            frm.setLastName(user.getLastName());
            frm.setPassword(this.password);
        }
        var creds = this.credentialService.getAllCredentials(this.frm.getUserid());
        if(creds == null || creds.size() < 1)
        {
            CredentialForm newCred = new CredentialForm();
            newCred.setUrl("https://localhost.dev");
            newCred.setUserId(frm.getUserid());
            newCred.setPassword(this.password);
            newCred.setUsername(this.username);
            this.credentialService.addCredential(newCred);
        }

    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAddCredential()
    {
        driver.get("http://localhost:" + this.port + "/login");
        var creds = this.credentialService.getAllCredentials(this.frm.getUserid());
        int count = creds != null ? creds.size() : 0;

        LoginForm loginForm = new LoginForm(driver);
        loginForm.fillForm(frm.getUsername(),frm.getPassword());
        loginForm.doLogin();

        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

        CredentialTab credentialTab = new CredentialTab(driver);
        credentialTab.openTab();

        credentialTab.showAddDialog(driver);
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until((x) -> x.findElement(By.id("credential-password")).isDisplayed());

        credentialTab.fillForm(null,"http://local.dev",this.username,this.password);
        credentialTab.submitForm(driver);

        creds =this.credentialService.getAllCredentials(this.frm.getUserid());
        Assertions.assertNotNull(creds);
        Assertions.assertEquals(creds.size(),count + 1);

    }
    @Test
    public void testEditCredential()
    {
        driver.get("http://localhost:" + this.port + "/login");
        var creds = this.credentialService.getAllCredentials(this.frm.getUserid());
        int count = creds != null ? creds.size() : 0;

        LoginForm loginForm = new LoginForm(driver);
        loginForm.fillForm(frm.getUsername(),frm.getPassword());
        loginForm.doLogin();

        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

        CredentialTab credentialTab = new CredentialTab(driver);
        credentialTab.openTab();
        credentialTab.showEditDialog(driver);
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until((x) -> x.findElement(By.id("credential-password")).isDisplayed());

        credentialTab.fillForm(null,"http://local.dev",this.username,this.password);
        credentialTab.submitForm(driver);

        creds =this.credentialService.getAllCredentials(this.frm.getUserid());
        Assertions.assertNotNull(creds);
        Assertions.assertEquals(count,creds.size());

    }

    @Test
    public void testDeleteCredential()
    {
        driver.get("http://localhost:" + this.port + "/login");

        var creds = this.credentialService.getAllCredentials(this.frm.getUserid());
        int count = creds != null ? creds.size() : 0;

        LoginForm loginForm = new LoginForm(driver);
        loginForm.fillForm(frm.getUsername(),frm.getPassword());
        loginForm.doLogin();

        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

        CredentialTab credentialTab = new CredentialTab(driver);
        credentialTab.openTab();
        credentialTab.showDeleteDialog(driver);

        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until((x) -> x.findElement(By.id("confirm-delete-link")).isDisplayed());
        WebElement deleteElement = driver.findElement(By.id("confirm-delete-link")) ;
        deleteElement.click();

        creds =this.credentialService.getAllCredentials(this.frm.getUserid());
        Assertions.assertNotNull(creds);
        Assertions.assertEquals(count -1 ,creds.size());

    }

}