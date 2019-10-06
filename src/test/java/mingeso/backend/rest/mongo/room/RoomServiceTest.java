package mingeso.backend.rest.mongo.room;

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

import java.util.Optional;

@RunWith(SpringRunner.class)
public class RoomServiceTest {

  @TestConfiguration
  static class RoomServiceTestContextConfiguration {

    @Bean
    public RoomService roomService() {
      return new RoomService();
    }
  }

  @Autowired
  private RoomService service;

  @MockBean
  private RoomRepository repository;

  @Before
  public void setUp() {
    Room room = new Room();
    room.setId("5d9a411d7d365f68746fc3d3");
    Optional<Room> optionalRoom = Optional.of(room);

    Mockito.when(repository.findById(room.getId()))
      .thenReturn(optionalRoom);
  }

  @Test
  public void whenValidIdThenRoomShouldBeFound() {
    String id = "5d9a411d7d365f68746fc3d3";
    Room found = service.getById(id);
    Assertions.assertThat(found.getId()).isEqualTo(id);
  }

}
