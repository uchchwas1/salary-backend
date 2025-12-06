package org.example.salarysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class SalaryBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalaryBackendApplication.class, args);
	}

    // generate password hash
    @Bean
    public CommandLineRunner run() {
        return args -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String rawPassword = "password";
            String encodedPassword = encoder.encode(rawPassword);

            System.out.println("==========================================");
            System.out.println("GENERATED VALID HASH FOR 'password':");
            System.out.println(encodedPassword);
            System.out.println("==========================================");
        };
    }

}
