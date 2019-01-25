package pro.antonvmax.xspringrestjpa.hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HotelController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping("/hotels")
    List<Hotel> allHotes() {
        logger.info(this.getClass() + " :: GET allHotels()");
        return hotelRepository.findAll();
    }

    @PostMapping("/hotels")
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    String addHotel(@RequestBody @Valid Hotel hotel) {
        logger.info(this.getClass() + " :: POST addHotel() : " + hotel);
        return hotelRepository.save(hotel).getId();
    }

    @GetMapping("/hotels/{id}")
    Hotel getHotel(@PathVariable String id) {
        logger.info(this.getClass() + " :: GET getHotel()");
        return hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));
    }

    @PutMapping("/hotels/{id}")
    Hotel updHotel(@RequestBody @Valid Hotel hotel, @PathVariable String id) {
        logger.info(this.getClass() + " :: PUT updHotel()");
        return hotelRepository.save(hotel);
    }

    @DeleteMapping("/hotels/{id}")
    void delHotel(@PathVariable String id) {
        logger.info(this.getClass() + " :: DELETE delHotel()");
        hotelRepository.deleteById(id);
    }
}

class HotelNotFoundException extends RuntimeException {
    HotelNotFoundException(String id) {
        super("Could not find hotel '" + id + "'");
    }
}
