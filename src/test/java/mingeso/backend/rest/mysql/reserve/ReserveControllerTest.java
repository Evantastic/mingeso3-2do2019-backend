package mingeso.backend.rest.mysql.reserve;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@RunWith(SpringRunner.class)
@WebMvcTest(ReserveController.class)
@AutoConfigureDataJpa
public class ReserveControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReserveService service;

    @Autowired
    private ObjectMapper mapper;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final int ID = 7777;
    private static final int CLIENTID = 11;
    private static final String ROOMID = "bashkjdb43ilnas34asf23";
    private static final LocalDate STARTDATE = LocalDate.parse("29-01-2012", formatter);
    private static final LocalDate ENDDATE = LocalDate.parse("01-01-2019",formatter);
    private static final int PRICE = 10000;
    private static final String URL = "/api/rest/mysql/reserves";


    @Test
    public void givenReserveWhenGetReservesThenReturnJsonArray(){
        Reserve reserve = new Reserve();
        List<Reserve> allReserves = new ArrayList<>();
        reserve.setClientId(CLIENTID);
        allReserves.add(reserve);
        given(service.getAll()).willReturn(allReserves);
        try {
            mvc.perform(get(URL)
                    .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$",
                            hasSize(1)))
                    .andExpect(jsonPath("$[0].clientId",
                            is(CLIENTID)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIdWhenGetReserveByIdThenReturnJson(){
        Reserve reserve = new Reserve();
        reserve.setId(ID);
        given(service.getById(ID)).willReturn(reserve);
        try {
            mvc.perform(get(URL + "/" + ID)
                    .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id",
                            is(ID)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenCreateReserveThenReturnJson(){
        Reserve reserve = new Reserve();
        reserve.setId(ID);
        reserve.setClientId(CLIENTID);
        reserve.setRoomId(ROOMID);
        reserve.setStartDate(STARTDATE);
        reserve.setEndDate(ENDDATE);
        reserve.setPrice(PRICE);
        given(service.create(reserve)).willReturn(reserve);
        try {
            mvc.perform(post(URL)
                    .contentType(APPLICATION_JSON)
                    .content(mapper.writeValueAsString(reserve)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id",
                            is(reserve.getId())))
                    .andExpect(jsonPath("$.clientId",
                            is(reserve.getClientId())))
                    .andExpect(jsonPath("$.roomId",
                            is(reserve.getRoomId())))
                    //.andExpect(jsonPath("$.startDate",
                            //is(reserve.getStartDate())))
                    //.andExpect(jsonPath("$.endDate",
                            //is(reserve.getEndDate())))
                    .andExpect(jsonPath("$.price",
                            is(reserve.getPrice())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void whenDeleteReserveThenReturnJson(){
        Reserve reserve = new Reserve();
        reserve.setId(ID);
        reserve.setClientId(CLIENTID);
        given(service.delete(reserve.getId())).willReturn(reserve);
        try {
            mvc.perform(delete(URL + "/" + ID)
                    .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.clientId",
                            is(reserve.getClientId())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




































}
