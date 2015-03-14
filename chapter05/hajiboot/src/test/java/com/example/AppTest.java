package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class AppTest {

  @Value("${local.server.port}")
  int port;

  RestTemplate restTemplate = new TestRestTemplate();

  @Test
  public void testHome() throws Exception {
    // Setup & Exercise
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port, String.class);
    // Verify
    Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK));
    Assert.assertThat(response.getBody(), is("Hello, Spring Boot!!!"));
  }
}