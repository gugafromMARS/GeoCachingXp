package gsc.projects.cachesmcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class CachesMcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachesMcsApplication.class, args);
	}

}
