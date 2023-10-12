package manhar.laziaf.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Simple Bank Accounts Microservice REST API Documentation",
				description = "Simple Bank Accounts Microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Muhammad Faizal",
						email = "faizalabdrahman@gmail.com",
						url = "https://www.linkedin.com/in/faizalabdrahman/"
				)
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
