package com.joe.reactive.reactclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ReactClientApplication {


	@Bean
	WebClient webClient(){
		return WebClient.create("http://localhost:8082/rest/employee");
	}

	@Bean
	CommandLineRunner commandLineRunner(WebClient webClient){
		return args -> {
			webClient
					.get()
					.uri("/all")
					.retrieve()
					.bodyToFlux(Employee.class)
					.filter(emp-> emp.getName().equalsIgnoreCase("Joseph"))
					.flatMap(emp->{
						return webClient
								.get()
								.uri("/{id}/events",emp.getId())
								.retrieve()
								.bodyToFlux(EmployeeEvent.class);
					})
					.subscribe(employeeEvent->System.out.println(employeeEvent.toString()));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactClientApplication.class, args);
	}

}
