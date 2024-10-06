package banking.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("banking.example.repository")
@SpringBootApplication(scanBasePackages = "banking.example")
public class BankApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankApiApplication.class, args);
    }
}