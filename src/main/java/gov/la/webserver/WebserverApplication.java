package gov.la.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebserverApplication {

	public static void main(String[] args) {
		System.out.println("Hello world");
		SpringApplication.run(WebserverApplication.class, args);
	}

}
