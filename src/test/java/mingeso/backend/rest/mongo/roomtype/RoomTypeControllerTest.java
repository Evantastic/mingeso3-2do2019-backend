package mingeso.backend.rest.mongo.roomtype;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(RoomTypeController.class)
@AutoConfigureDataMongo
public class RoomTypeControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private RoomTypeService service;

  @Autowired
  private ObjectMapper mapper;

  private static final String ID = "5d9a411d7d365f68746fc3d3";
  private static final String TITLE = "type1";
  private static final String URL = "/api/rest/mongo/roomtypes";
  private static final int NUMBER = 50;

  @Test
  public void givenRoomWhenGetRoomsThenReturnJsonArray(){
    RoomType roomType = new RoomType();
    List<RoomType> allRoomTypes = new ArrayList<>();
    roomType.setTitle(TITLE);
    allRoomTypes.add(roomType);
    given(service.getAll()).willReturn(allRoomTypes);
    try {
      mvc.perform(get(URL)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$",
          hasSize(1)))
        .andExpect(jsonPath("$[0].title",
          is(TITLE)));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void givenIdWhenGetRoomByIdThenReturnJson(){
    RoomType roomType = new RoomType();
    roomType.setId(ID);
    given(service.getById(ID)).willReturn(roomType);
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
  public void whenCreateRoomThenReturnJson() throws UnsupportedEncodingException, JsonProcessingException {
    RoomType roomType = new RoomType(ID, TITLE, TITLE, NUMBER, TITLE, NUMBER, NUMBER,
      Collections.singletonList(TITLE));
    given(service.create(roomType)).willReturn(roomType);
    MvcResult result = null;
    try {
      result = mvc.perform(post(URL)
        .contentType(APPLICATION_JSON)
        .content(mapper.writeValueAsString(roomType)))
        .andExpect(status().isOk())
        .andReturn();
    } catch (Exception e) {
      e.printStackTrace();
    }
    assert result != null;
    String response = result.getResponse().getContentAsString();
    Assertions.assertThat(mapper.writeValueAsString(roomType))
      .isEqualToIgnoringWhitespace(response);
  }

  @Test
  public void whenDeleteRoomThenReturnJson(){
    RoomType roomType = new RoomType();
    roomType.setId(ID);
    roomType.setTitle(TITLE);
    given(service.delete(roomType.getId())).willReturn(roomType);
    try {
      mvc.perform(delete(URL + "/" + ID)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title",
          is(roomType.getTitle())));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
