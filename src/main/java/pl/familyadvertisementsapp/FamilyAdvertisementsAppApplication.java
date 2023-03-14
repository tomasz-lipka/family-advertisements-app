package pl.familyadvertisementsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "pl.familyadvertisementsapp")
public class FamilyAdvertisementsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyAdvertisementsAppApplication.class, args);
    }

}
