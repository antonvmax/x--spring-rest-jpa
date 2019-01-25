package pro.antonvmax.xspringrestjpa.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

interface HotelRepository extends JpaRepository<Hotel, String> {
}
