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

import java.util.*;

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

  private final static String id = "5d9a411d7d365f68746fc3d3";

  @Before
  public void setUpFindById() {
    Room room = new Room();
    room.setId(id);
    Optional<Room> optionalRoom = Optional.of(room);

    Mockito.when(repository.findById(room.getId()))
      .thenReturn(optionalRoom);
  }

  @Before
  public void setUpCreate() {
    Room room = new Room();
    Room found = new Room();
    found.setId(id);
    Mockito.when(repository.save(room)).thenReturn(found);
  }

  @Before
  public void setUpGetAll() {
    Room room1 = new Room();
    Room room2 = new Room();
    Room room3 = new Room();
    List<Room> rooms = new ArrayList<>();
    rooms.add(room1);
    rooms.add(room2);
    rooms.add(room3);
    Mockito.when(repository.findAll()).thenReturn(rooms);
  }

  @Test
  public void whenValidIdRoomThenRoomShouldBeFound() {
    Room found = service.getById(id);
    Assertions.assertThat(found.getId()).isEqualTo(id);
  }

  @Test
  public void whenCreateRoomThenRoomShouldBeReturned() {
    Room room = new Room();
    Room found = service.create(room);
    Assertions.assertThat(found.getId()).isEqualTo(id);
  }

  @Test
  public void whenFindAllRoomThenReturnAll() {
    List<Room> found = service.getAll();
    Assertions.assertThat(found.size()).isEqualTo(3);
  }

}
