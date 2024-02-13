package me.tamsil.validator;

import me.tamsil.validator.domain.Employee;
import me.tamsil.validator.domain.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HibernateValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateValidatorApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(EmployeeRepository employeeRepository) {
		return (String[] args) -> {
			Employee employee1 = new Employee("test01", "test01@gmail.com", "010-0000-0000", 40, "MALE", "Engineer", "Seoul");
			Employee employee2 = new Employee("test02", "test02@gmail.com", "010-0000-0000", 40, "MALE", "Engineer", "Seoul");

			employeeRepository.save(employee1);
			employeeRepository.save(employee2);
			employeeRepository.findAll().forEach(System.out::println);
		};
	}
}
