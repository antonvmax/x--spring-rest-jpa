package pro.antonvmax.xspringrestjpa.hotel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class HotelControllerUnitTest {

    @Mock
    private HotelRepository hotelRepository;

    private MockMvc mockMvc;

    private Hotel hotel1 = new Hotel("Hayat");
    private Hotel hotel2 = new Hotel("Radisson");

    private final String hotel1Id = "hhh";
    private final String hotel2Id = "rrr";

    private Hotel hotel3 = new Hotel("Ararat");
    // проще-быстрее
    private final String hotelJSONstring = "{\n" +
            "\"id\": \"new\",\n" +
            "\"name\": \"Ararat\",\n" +
            "\"catid\":\"dsfsd\",\n" +
            "\"point\": [3.2,-3.14],\n" +
            "\"addr\": \"sgsfdg\",\n" +
            "\"img\": \"sdfsdg\",\n" +
            "\"site\": {\"label\": \"hi\",\"url\": \"there\"},\n" +
            "\"services\": [\"restaurant\", \"bar\"]\n" +
            "}";
    private final String hotel3Id = "aaa";

    private List<Hotel> hotels = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new HotelController(hotelRepository))
                .build();

        hotel1.setId(hotel1Id);
        hotel2.setId(hotel2Id);

        hotels = Arrays.asList(hotel1, hotel2);

        hotel3.setId(hotel3Id);
    }

    @Test
    public void GIVEN__MockedRepository__WHEN__methodGET__THEN__allHotelsReturned() throws Exception {
        when(hotelRepository.findAll()).thenReturn(hotels);
        mockMvc.perform(get("/hotels").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].name").value("Hayat"))
                .andExpect(jsonPath("$[1].name").value("Radisson"));
        // ...и тому подобное для остальных полей
    }

    @Test
    public void GIVEN__MockedRepository__WHEN__methodPOST__THEN__addedHotelReturned() throws Exception {
        when(hotelRepository.save(any())).thenReturn(hotel3);
        mockMvc.perform(post("/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(hotelJSONstring))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(hotel3.getId()));
    }

    @Test
    public void GIVEN__MockedRepository__WHEN__methodGET_withId__THEN__hotelByIdReturned() throws Exception {
        when(hotelRepository.findById(hotel1.getId())).thenReturn(java.util.Optional.of(hotel1));
        mockMvc.perform(get("/hotels/" + hotel1.getId()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(hotel1.getId()))
                .andExpect(jsonPath("$.name").value(hotel1.getName()));
    }

    // ...и тому подобное для остальных методов
}
