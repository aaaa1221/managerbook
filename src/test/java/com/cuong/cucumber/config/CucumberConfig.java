package com.cuong.cucumber.config;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;

import com.cuong.BookCqrsCucumberJunitErrorHandlingApplication;


@ContextConfiguration
@SpringBootTest(classes = BookCqrsCucumberJunitErrorHandlingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class CucumberConfig {

    @Autowired
    public TestRestTemplate testRestTemplate;

    @LocalServerPort
    public int port;

    public String staticURL = "http://localhost:";

    public HttpHeaders httpHeaders;

    @Before
    public void setUp() throws Exception {
        httpHeaders = new HttpHeaders();
    }
}
