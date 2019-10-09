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

  private static final String PRESIDENTIAL = "presidencial";
  private static final String KING = "rey";

  @Before
  public void setUpGetTypesOfRoom() {
    List<Room> found = new ArrayList<>();
    Room room1 = new Room();
    room1.setType(PRESIDENTIAL);
    Room room2 = new Room();
    room2.setType(KING);
    Room room3 = new Room();
    room3.setType(KING);
    found.add(room1);
    found.add(room2);
    found.add(room3);
    Mockito.when(repository.findAll()).thenReturn(found);
  }

  @Before
  public void setUpGetRoomByType() {
    Room room = new Room();
    room.setType(PRESIDENTIAL);
    Optional<Room> found = Optional.of(room);
    Mockito.when(repository.findFirstByType(PRESIDENTIAL)).thenReturn(found);
  }

  @Test
  public void whenGetTypesOfRoomReturnListOfStrings() {
    List<String> found = service.getTypesOfRoom();
    List<String> types = new ArrayList<>();
    types.add(KING);
    types.add(PRESIDENTIAL);
    Assertions.assertThat(found)
      .containsAll(types)
      .doesNotHaveDuplicates();
  }

  @Test
  public void whenGetRoomByTypeThenReturnCorrectRoom() {
    Room room = service.getRoomByType(PRESIDENTIAL);
    Assertions.assertThat(room.getType()).isEqualTo(PRESIDENTIAL);
  }

}
