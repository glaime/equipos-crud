package ar.com.demo.dux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ar.com.demo.dux"})
public class DuxApplication {

	public static void main(String[] args) {
		SpringApplication.run(DuxApplication.class, args);
	}

}
