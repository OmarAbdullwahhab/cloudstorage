package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.viewmodels.NoteForm;
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
public class NotesTabTests {

      @LocalServerPort
      private int port;

      @Autowired
      private NoteService noteService;

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
          var notes = this.noteService.getAllNotes(this.frm.getUserid());
          if(notes == null || notes.size() < 1)
          {
              NoteForm noteForm = new NoteForm();
              noteForm.setTitle("Note Title");
              noteForm.setDescription("Note Descripption");
              noteForm.setUserId(this.frm.getUserid());
              this.noteService.addNote(noteForm);
          }

      }

      @AfterEach
      public void afterEach() {
          if (this.driver != null) {
              driver.quit();
          }
      }

      @Test
      public void testAddNote()
      {
          driver.get("http://localhost:" + this.port + "/login");
          var notes = this.noteService.getAllNotes(this.frm.getUserid());
          int count = notes != null ? notes.size() : 0;

          LoginForm loginForm = new LoginForm(driver);
          loginForm.fillForm(frm.getUsername(),frm.getPassword());
          loginForm.doLogin();

          Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

          NoteTab noteForm = new NoteTab(driver);
          noteForm.openTab();

          noteForm.showAddDialog(driver);
          WebDriverWait wait = new WebDriverWait(driver,5);
          wait.until((x) -> x.findElement(By.id("note-title")).isDisplayed());

          noteForm.fillForm(null,"Test Note","Test Note Desc");
          noteForm.submitForm(driver);

          notes =this.noteService.getAllNotes(this.frm.getUserid());
          Assertions.assertNotNull(notes);
          Assertions.assertEquals(notes.size(),count + 1);

      }
    @Test
    public void testEditNote()
    {
        driver.get("http://localhost:" + this.port + "/login");
        var notes = this.noteService.getAllNotes(this.frm.getUserid());
        int count = notes != null ? notes.size() : 0;

        LoginForm loginForm = new LoginForm(driver);
        loginForm.fillForm(frm.getUsername(),frm.getPassword());
        loginForm.doLogin();

        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

        NoteTab noteForm = new NoteTab(driver);
        noteForm.openTab();
        noteForm.showEditDialog(driver);
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until((x) -> x.findElement(By.id("note-title")).isDisplayed());

        WebElement idElement = driver.findElement(By.id("note-id")) ;
        noteForm.fillForm(idElement.getText(),"Test Note 2","Test Note 2 Desc");
        noteForm.submitForm(driver);

        notes =this.noteService.getAllNotes(this.frm.getUserid());
        Assertions.assertNotNull(notes);
        Assertions.assertEquals(count,notes.size());

    }

    @Test
    public void testDeleteNote()
    {
        driver.get("http://localhost:" + this.port + "/login");

        var notes = this.noteService.getAllNotes(this.frm.getUserid());
        int count = notes != null ? notes.size() : 0;

        LoginForm loginForm = new LoginForm(driver);
        loginForm.fillForm(frm.getUsername(),frm.getPassword());
        loginForm.doLogin();

        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

        NoteTab noteForm = new NoteTab(driver);
        noteForm.openTab();
        noteForm.showDeleteDialog(driver);

        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until((x) -> x.findElement(By.id("confirm-delete-link")).isDisplayed());
        WebElement deleteElement = driver.findElement(By.id("confirm-delete-link")) ;
        deleteElement.click();

        notes =this.noteService.getAllNotes(this.frm.getUserid());
        Assertions.assertNotNull(notes);
        Assertions.assertEquals(count -1 ,notes.size());

    }

  }