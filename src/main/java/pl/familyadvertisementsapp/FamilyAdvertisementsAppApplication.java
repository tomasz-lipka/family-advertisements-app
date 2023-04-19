package pl.familyadvertisementsapp;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class FamilyAdvertisementsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyAdvertisementsAppApplication.class, args);
    }
}
