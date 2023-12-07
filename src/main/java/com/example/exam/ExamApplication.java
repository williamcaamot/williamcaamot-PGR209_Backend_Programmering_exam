package com.example.exam;

import com.example.exam.MachineFaker.MachineFaker;
import com.example.exam.Model.*;
import com.example.exam.Repo.*;
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
            PartRepository partRepository,
            SubassemblyRepository subassemblyRepository,
            MachineRepository machineRepository,
            OrderRepository orderRepository){
        MachineFaker faker = new MachineFaker();
            for(int i = 0; i < 20; i++){
                partRepository.save(new Part(faker.partName(), faker.partDescription()));

                subassemblyRepository.save(new Subassembly(faker.subassemblyName(), faker.subassemblyDescription()));

                machineRepository.save(new Machine(faker.machineName(), faker.machineDescription()));

                Customer customer = new Customer(faker.superhero().name(),faker.internet().emailAddress());
                CustomerOrder customerOrder = new CustomerOrder("Order " + i,  customer);

                customerOrder.setCustomer(customer);
                customer.getCustomerOrders().add(customerOrder);

                customerRepository.save(customer);

            }

        return null;
    }





}
