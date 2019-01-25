package pro.antonvmax.xspringrestjpa.hotel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelControllerSmokeTest {

    @Autowired
    private HotelController hotelController;

    @Test
    public void TEST_Autowired_success() throws Exception {
        assertNotNull(hotelController);
    }

}
