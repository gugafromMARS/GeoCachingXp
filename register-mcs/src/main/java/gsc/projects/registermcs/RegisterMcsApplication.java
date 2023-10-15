package gsc.projects.registermcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RegisterMcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterMcsApplication.class, args);
	}

}
