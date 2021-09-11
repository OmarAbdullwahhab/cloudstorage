package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.viewmodels.SignupForm;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesWebFormsTest {

      @LocalServerPort
      private int port;

      @Autowired
      private NoteService noteService;

      @Autowired
      private UserService userService;

      @Autowired
      private EncryptionService encryptionService;

      private WebDriver driver;
      SignupForm frm;

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
              System.out.println("User id = " + this.frm.getUserid());
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

      }

      @AfterEach
      public void afterEach() {
          if (this.driver != null) {
              driver.quit();
          }
         // this.noteService.deleteNote()
         // this.userService.deleteUser(this.frm.getUsername());
      }

      @Test
      public void testAddNote()
      {
          driver.get("http://localhost:" + this.port + "/login");


          var notes = this.noteService.getAllNotes(this.frm.getUserid());
          int count = notes != null ? notes.size() : 0;
          //login first
          LoginForm loginForm = new LoginForm(driver);
          loginForm.fillForm(frm.getUsername(),frm.getPassword());
          loginForm.doLogin();

          //assert for login success and user directed to the home page
          Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

          //add a note.
          NoteTab noteForm = new NoteTab(driver);
          noteForm.openTab();



          noteForm.showAddDialog(driver);
          WebDriverWait wait = new WebDriverWait(driver,100);
          wait.until((x) -> x.findElement(By.id("note-title")).isDisplayed());

          noteForm.fillForm(null,"Test Note","Test Note Desc");


          noteForm.submitForm(driver);

          ///wait.until((x) -> x.findElement(By.id("note-title")).isDisplayed());
          //ensure that the note is correctly added.
          notes =this.noteService.getAllNotes(this.frm.getUserid());
          Assertions.assertNotNull(notes);
          Assertions.assertEquals(notes.size(),count + 1);

      }




  }