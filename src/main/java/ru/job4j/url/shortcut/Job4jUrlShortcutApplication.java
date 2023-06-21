package ru.job4j.url.shortcut;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class Job4jUrlShortcutApplication {

	@Bean
	public SpringLiquibase liquibase(DataSource ds) {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setDataSource(ds);
		liquibase.setChangeLog("classpath:db/liquibase-changeLog.xml");
		return liquibase;
	}

	public static void main(String[] args) {
		SpringApplication.run(Job4jUrlShortcutApplication.class, args);
	}
}
