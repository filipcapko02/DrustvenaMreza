package rs.ac.uns.ftn.svtvezbe07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SvtVezbe07Application {

	public static void main(String[] args) {
		SpringApplication.run(SvtVezbe07Application.class, args);
	}

}
