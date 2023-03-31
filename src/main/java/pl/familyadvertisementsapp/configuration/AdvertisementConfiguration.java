package pl.familyadvertisementsapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.familyadvertisementsapp.model.Advertisement;

@Configuration
public class AdvertisementConfiguration {

    @Bean
    public Advertisement getAdvertisement() {
        return new Advertisement();
    }
}
