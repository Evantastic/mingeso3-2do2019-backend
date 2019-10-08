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

  private final String presidencial = "presidencial";
  private final String rey = "rey";

  @Before
  public void setUpGetTypesOfRoom() {
    List<Room> found = new ArrayList<>();
    Room room1 = new Room();
    room1.setType(this.presidencial);
    Room room2 = new Room();
    room2.setType(this.rey);
    Room room3 = new Room();
    room3.setType(this.rey);
    found.add(room1);
    found.add(room2);
    found.add(room3);
    Mockito.when(repository.findAll()).thenReturn(found);
  }

  @Before
  public void setUpGetRoomByType() {
    Room room = new Room();
    room.setType(this.presidencial);
    Optional<Room> found = Optional.of(room);
    Mockito.when(repository.findFirstByType(this.presidencial)).thenReturn(found);
  }

  @Test
  public void whenGetTypesOfRoomReturnListOfStrings() {
    List<String> found = service.getTypesOfRoom();
    List<String> types = new ArrayList<>();
    types.add(this.rey);
    types.add(this.presidencial);
    Assertions.assertThat(found)
      .containsAll(types)
      .doesNotHaveDuplicates();
  }

  @Test
  public void whenGetRoomByTypeThenReturnCorrectRoom() {
    Room room = service.getRoomByType(this.presidencial);
    Assertions.assertThat(room.getType()).isEqualTo(this.presidencial);
  }

}
