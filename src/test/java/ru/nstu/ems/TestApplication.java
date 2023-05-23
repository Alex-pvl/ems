package ru.nstu.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
public class TestApplication {
	@Bean
	@RestartScope
	@ServiceConnection
	PostgreSQLContainer postgreSQLContainer() {
		return new PostgreSQLContainer("postgres:15.2");
	}

	public static void main(String[] args) {
		SpringApplication
			.from(Application::main)
			.with(TestApplication.class)
			.run(args);
	}
}
