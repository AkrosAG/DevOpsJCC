package ch.akros.jcc.rest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import ch.akros.jcc.model.Greeting;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GreetingControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void greeting_shouldGreetHelloWorld() {
		final ResponseEntity<Greeting> response = restTemplate.getForEntity("/greeting", Greeting.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		final Greeting greeting = response.getBody();
		assertEquals("Hello, World!", greeting.getContent());
	}

	@Test
	public void greeting_withName_shouldGreetHello() {
		final Map<String, String> params = new HashMap<>();
		params.put("name", "jUnit");

		final ResponseEntity<Greeting> response = restTemplate.getForEntity("/greeting?name={name}", Greeting.class,
				params);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		final Greeting greeting = response.getBody();
		assertEquals("Hello, jUnit!", greeting.getContent());
	}
}
