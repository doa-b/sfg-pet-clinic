package guru.springframework.sfgpetclinic.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Doa on 27-5-2019.
 */
@Configuration
public class FakerConfig {

    @Bean
    public Faker faker() {
        return new Faker();
    }
}
