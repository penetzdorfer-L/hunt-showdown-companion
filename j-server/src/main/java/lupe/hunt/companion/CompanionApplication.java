package lupe.hunt.companion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class CompanionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanionApplication.class, args);
	}

}