package lupe.companion.hunt;

import lupe.companion.hunt.loadout.LoadoutController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class HuntApplication {

	public static void main(String[] args) {
		SpringApplication.run(HuntApplication.class, args);
	}

}
