package lupe.hunt.companion.chaosLoadout.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurator {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
