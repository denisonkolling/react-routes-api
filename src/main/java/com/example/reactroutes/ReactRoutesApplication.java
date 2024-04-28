package com.example.reactroutes;

import com.example.reactroutes.model.Customer;
import com.example.reactroutes.model.Gender;
import com.example.reactroutes.repository.CustomerRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;

@SpringBootApplication
public class ReactRoutesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactRoutesApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(
			CustomerRepository customerRepository,
			PasswordEncoder passwordEncoder) {
		return args -> {
			createRandomCustomer(customerRepository, passwordEncoder);
		};
	}

	private static void createRandomCustomer(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
		var faker = new Faker();
		Random random = new Random();
		Name name = faker.name();
		String firstName = name.firstName();
		String lastName = name.lastName();
		int age = random.nextInt(83) + 16;
		String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@email.com";
		Customer customer = new Customer(
				firstName + " " + lastName,
				email,
				passwordEncoder.encode("password"));
		customerRepository.save(customer);
		System.out.println(email);
	}
}
