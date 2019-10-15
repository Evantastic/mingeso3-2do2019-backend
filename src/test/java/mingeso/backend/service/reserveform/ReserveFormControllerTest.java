package mingeso.backend.service.reserveform;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import mingeso.backend.rest.mysql.reserve.Reserve;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReserveFormController.class)
@AutoConfigureDataMongo
@AutoConfigureDataJpa
public class ReserveFormControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReserveFormService service;

    @Autowired
    private ObjectMapper mapper;


    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @JsonFormat(pattern = "dd-MM-yyyy")
    private static final LocalDate STARTDATE = LocalDate.parse("29-01-2012", formatter);
    @JsonFormat(pattern = "dd-MM-yyyy")
    private static final LocalDate ENDDATE = LocalDate.parse("01-01-2019",formatter);
    private static final int ROOMNUMBER = 101;

    private static final String URL = "/api/services/reserves/form";

    @Test
    public void givenMakeReservationWhenGetMakeReservationThenReturnJsonArrayOfReserve(){
        ReserveForm reserveForm = new ReserveForm();
        reserveForm.setStartDate(STARTDATE);
        reserveForm.setEndDate(ENDDATE);
        reserveForm.setRoomNumber(ROOMNUMBER);
        Reserve reserve = new Reserve();
        given(service.makeReservation(reserveForm)).willReturn(reserve);
        /*try {
            mvc.perform(post(URL)
                    .contentType(APPLICATION_JSON)
                    .content(mapper.writeValueAsString(reserve)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.startDate",
                    is(reserve.getStartDate())))
                    .andExpect(jsonPath("$.endDate",
                    is(reserve.getEndDate())));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

























}
