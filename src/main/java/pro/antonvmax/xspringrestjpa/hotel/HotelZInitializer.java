package pro.antonvmax.xspringrestjpa.hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HotelZInitializer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    CommandLineRunner initHotelRepository(HotelRepository hotelRepository) {
        return args -> {
            logger.info("Preloading hotel: " + hotelRepository.save(new Hotel("Hayat")));
            logger.info("Preloading hotel: " + hotelRepository.save(new Hotel("Radisson")));
        };
    }
}
