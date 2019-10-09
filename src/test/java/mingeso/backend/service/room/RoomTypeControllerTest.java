package mingeso.backend.service.room;

import com.fasterxml.jackson.databind.ObjectMapper;
import mingeso.backend.rest.mongo.room.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(RoomTypeController.class)
@AutoConfigureDataMongo
public class RoomTypeControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private RoomTypeService service;

  private static final String URL = "/api/service/rooms/types";
  private static final String TYPE1 = "type1";
  private static final String TYPE2 = "type2";

  @Test
  public void givenTwoTypesOfRoomWhenGetTypesOfRoomReturnJsonArray()
    throws Exception {
    List<String> types = Arrays.asList(TYPE1, TYPE2);
    given(service.getTypesOfRoom()).willReturn(types);
    mvc.perform(get(URL)
      .contentType(APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(2)))
      .andExpect(jsonPath("$[0]",
        is(TYPE1)))
      .andExpect(jsonPath("$[1]",
        is(TYPE2)));
  }

  @Test
  public void givenRoomWithTypeWhenGetByTypeThenReturnRoom() throws Exception {
    Room room = new Room();
    room.setType(TYPE1);
    given(service.getRoomByType(TYPE1)).willReturn(room);
    mvc.perform(get(URL + "/" + TYPE1)
      .contentType(APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.type",
        is(TYPE1)));
  }
}
