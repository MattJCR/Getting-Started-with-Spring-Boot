package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student mateo = new Student("Mateo", LocalDate.of(1991,4,14),"mateo@email.com"
            );
            Student sara = new Student(
                    "Sara", LocalDate.of(1991,2,21),"sara@email.com"
            );

            repository.saveAll(List.of(mateo,sara));
        };
    }

}
