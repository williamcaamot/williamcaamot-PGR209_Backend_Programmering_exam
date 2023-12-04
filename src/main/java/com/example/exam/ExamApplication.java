package com.example.exam;

import com.example.exam.MachineFaker.MachineFaker;
import com.example.exam.Model.Part;
import com.example.exam.Repo.AddressRepository;
import com.example.exam.Repo.CustomerRepository;
import com.example.exam.Repo.PartRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }



    @Bean //THIS IS NOT SOMETHING THAT WE WOULD HAVE IN A REAL APPLICATION; ONLY FOR DEMO APPLICATION
    CommandLineRunner commandLineRunner(
            CustomerRepository customerRepository,
            AddressRepository addressRepository,
            PartRepository partRepository){
        MachineFaker faker = new MachineFaker();
            for(int i = 0; i < 20; i++){
                partRepository.save(new Part(faker.partName(), faker.partDescription()));
            }

        return null;
    }





}
