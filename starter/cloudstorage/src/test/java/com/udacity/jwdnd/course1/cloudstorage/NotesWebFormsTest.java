package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.controllers.NotesController;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.viewmodels.SignupForm;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

      private WebDriver driver;
      SignupForm frm;
      @BeforeAll
      static void beforeAll() {
          WebDriverManager.chromedriver().setup();

      }

      @BeforeEach
      public void beforeEach() {
          this.driver = new ChromeDriver();
           frm = new SignupForm();
          frm.setUsername("testuser");
          frm.setPassword("testuser");
          frm.setFirstName("Tystem");
          frm.setLastName("Tester");
          this.userService.createUser(frm);

      }

      @AfterEach
      public void afterEach() {
          if (this.driver != null) {
              driver.quit();
          }
          this.userService.deleteUser(this.frm.getUsername());
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
          NoteForm noteForm = new NoteForm(driver);
          noteForm.fillForm(null,"Test Note","Test Note Desc");
          noteForm.submitForm();

          //ensure that the note is correctly added.
          notes =this.noteService.getAllNotes(this.frm.getUserid());
          Assertions.assertNotNull(notes);
          Assertions.assertEquals(notes.size(),count + 1);

      }


  }