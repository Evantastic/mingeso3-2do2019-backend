package mingeso.backend.rest.mongo.room;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
  private static final String TITLE = "type1";
  private static final String URL = "/api/rest/mongo/rooms";
  private static final int NUMBER = 50;

  @Test
  public void givenRoomWhenGetRoomsThenReturnJsonArray() throws Exception {
    Room roomType = new Room();
    List<Room> allRooms = new ArrayList<>();
    roomType.setTitle(TITLE);
    allRooms.add(roomType);
    given(service.getAll()).willReturn(allRooms);
    mvc.perform(get(URL)
      .contentType(APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$",
        hasSize(1)))
      .andExpect(jsonPath("$[0].title",
        is(TITLE)));
  }

  @Test
  public void givenIdWhenGetRoomByIdThenReturnJson() throws Exception {
    Room roomType = new Room();
    roomType.setId(ID);
    given(service.getById(ID)).willReturn(roomType);
    mvc.perform(get(URL + "/" + ID)
      .contentType(APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id",
        is(ID)));
  }

  @Test
  public void whenCreateRoomThenReturnJson() throws Exception {
    Room room = new Room();
    room.setTitle(TITLE);
    room.setRoomNumber(NUMBER);
    room.setId(ID);
    room.setBeds(NUMBER);
    room.setCapacity(NUMBER);
    room.setDescription(TITLE);
    room.setPrice(NUMBER);
    room.setUrl(TITLE);
    room.setServices(Collections.singletonList(TITLE));
    given(service.create(room)).willReturn(room);
    mvc.perform(post(URL)
      .contentType(APPLICATION_JSON)
      .content(mapper.writeValueAsString(room)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id",
        is(room.getId())))
      .andExpect(jsonPath("$.title",
        is(room.getTitle())))
      .andExpect(jsonPath("$.roomNumber",
        is(room.getRoomNumber())))
      .andExpect(jsonPath("$.beds",
        is(room.getBeds())))
      .andExpect(jsonPath("$.capacity",
        is(room.getCapacity())))
      .andExpect(jsonPath("$.description",
        is(room.getDescription())))
      .andExpect(jsonPath("$.price",
        is(room.getPrice())))
      .andExpect(jsonPath("$.url",
        is(room.getUrl())))
      .andExpect(jsonPath("$.services",
        hasSize(1)))
      .andExpect(jsonPath("$.services[0]",
        is(room.getServices().get(0))));
  }

  @Test
  public void whenDeleteRoomThenReturnJson() throws Exception {
    Room roomType = new Room();
    roomType.setId(ID);
    roomType.setRoomNumber(NUMBER);
    given(service.delete(roomType.getId())).willReturn(roomType);
    mvc.perform(delete(URL + "/" + ID)
      .contentType(APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.roomNumber",
        is(roomType.getRoomNumber())));
  }
}
