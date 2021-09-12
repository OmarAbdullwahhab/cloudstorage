package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;


	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/login");

		Assertions.assertEquals("Login", driver.getTitle());

	}

	@Test
	public void testHomepageIsNotAccessibleBeforeLogin()
	{
		driver.get("http://localhost:" + this.port + "/home");

		Assertions.assertNotEquals("Home", driver.getTitle());
	}

	@Test
	public void testLogin() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/login");

		LoginForm frm = new LoginForm(driver);
		frm.fillForm("admin","admin");
		frm.doLogin();
		Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());
	}





}
