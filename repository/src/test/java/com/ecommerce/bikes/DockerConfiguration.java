package com.ecommerce.bikes;

import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
public abstract class DockerConfiguration {

    @LocalServerPort
    protected Integer randomServerPort;

    static {
        MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest")
                .withInitScript("test.sql")
                .withDatabaseName("name")
                .withUsername("user")
                .withPassword("pass");

        mysql.start();
        System.setProperty("spring.datasource.url", mysql.getJdbcUrl());
        System.setProperty("spring.datasource.username", mysql.getUsername());
        System.setProperty("spring.datasource.password", mysql.getPassword());
    }

    public String createUrl() {
        return "http://localhost:" + randomServerPort + "/";
    }
}
