package mingeso.backend.service.room;

import mingeso.backend.rest.mongo.room.Room;
import mingeso.backend.rest.mongo.room.RoomRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class RoomTypeServiceTest {

  @TestConfiguration
  static class RoomTypeServiceTestContextConfiguration {
    @Bean
    public RoomTypeService roomTypeService() {
      return new RoomTypeService();
    }
  }

  @Autowired
  private RoomTypeService service;

  @MockBean
  private RoomRepository repository;

  @Before
  public void setUpGetTypesOfRoom() {
    List<Room> found = new ArrayList<>();
    Room room1 = new Room();
    room1.setType("presidencial");
    Room room2 = new Room();
    room2.setType("rey");
    Room room3 = new Room();
    room3.setType("rey");
    found.add(room1);
    found.add(room2);
    found.add(room3);
    Mockito.when(repository.findAll()).thenReturn(found);
  }

  @Before
  public void setUpGetRoomByType() {
    String type = "presidencial";
    Room room = new Room();
    room.setType(type);
    Optional<Room> found = Optional.of(room);
    Mockito.when(repository.findFirstByType(type)).thenReturn(found);
  }

  @Test
  public void whenGetTypesOfRoomReturnListOfStrings() {
    List<String> found = service.getTypesOfRoom();
    List<String> types = new ArrayList<>();
    types.add("rey");
    types.add("presidencial");
    Assertions.assertThat(found)
      .containsAll(types)
      .doesNotHaveDuplicates();
  }

  @Test
  public void whenGetRoomByTypeThenReturnCorrectRoom() {
    String type = "presidencial";
    Room room = service.getRoomByType(type);
    Assertions.assertThat(room.getType()).isEqualTo(type);
  }

}
