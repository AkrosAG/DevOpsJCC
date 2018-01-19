package ch.akros.jcc.web;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.akros.jcc.model.Greeting;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebIT {

	private WebDriver driver;

	@BeforeClass
	public static void setUpBeforeAll() {
		WebDriverManager.phantomjs().setup();
	}

	@Before
	public void setUp() {
		driver = new PhantomJSDriver();
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void greeting_shouldGreetHelloWorld() throws IOException {
		driver.get("http://localhost:666/greeting");

		final WebElement element = driver.findElement(By.tagName("pre"));

		assertEquals("Hello, World!", mapResponseToObject(element.getText()).getContent());
	}

	@Test
	public void greeting_withName_shouldGreetHello() throws IOException {
		driver.get("http://localhost:666/greeting?name=jUnit");

		final WebElement element = driver.findElement(By.tagName("pre"));

		assertEquals("Hello, jUnit!", mapResponseToObject(element.getText()).getContent());
	}

	private Greeting mapResponseToObject(final String response) throws IOException {
		final ObjectMapper mapper = new ObjectMapper();

		return mapper.readValue(response, Greeting.class);
	}
}
