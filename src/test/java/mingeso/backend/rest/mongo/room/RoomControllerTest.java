package mingeso.backend.rest.mongo.room;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(RoomController.class)
@AutoConfigureDataMongo
public class RoomControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private RoomService service;

  @Autowired
  private ObjectMapper mapper;

  private static final String ID = "5d9a411d7d365f68746fc3d3";
  private static final String TYPE = "presidencial";
  private static final String URL = "/api/rest/rooms";
  private static final int NUMBER = 50;

  @Test
  public void givenRoomWhenGetRoomsThenReturnJsonArray() throws Exception {
    Room room = new Room();
    List<Room> allRooms = new ArrayList<>();
    room.setType(TYPE);
    allRooms.add(room);
    given(service.getAll()).willReturn(allRooms);
    mvc.perform(get(URL)
      .contentType(APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$",
        hasSize(1)))
      .andExpect(jsonPath("$[0].type",
        is(TYPE)));
  }

  @Test
  public void givenIdWhenGetRoomByIdThenReturnJson() throws Exception {
    Room room = new Room();
    room.setId(ID);
    given(service.getById(ID)).willReturn(room);
    mvc.perform(get(URL + "/" + ID)
      .contentType(APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id",
        is(ID)));
  }

  @Test
  public void whenCreateRoomThenReturnJson() throws Exception {
    Room room = new Room(ID, NUMBER, NUMBER, NUMBER, NUMBER,
      new ArrayList<>(), new ArrayList<>(),
      Arrays.asList(TYPE, TYPE), TYPE, TYPE, TYPE);
    given(service.create(room)).willReturn(room);
    MvcResult result = mvc.perform(post(URL)
      .contentType(APPLICATION_JSON)
      .content(mapper.writeValueAsString(room)))
      .andExpect(status().isOk())
      .andReturn();
    String response = result.getResponse().getContentAsString();
    Assertions.assertThat(mapper.writeValueAsString(room))
      .isEqualToIgnoringWhitespace(response);
  }
}
