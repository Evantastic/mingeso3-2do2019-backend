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

import java.util.ArrayList;
import java.util.List;
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

  private static final String ID = "5d9a411d7d365f68746fc3d3";
  private static final String INVALIDID = "5d9a411d7d365f68746fc315";

  @Before
  public void setUpFindById() {
    Room roomType = new Room();
    roomType.setId(ID);
    Optional<Room> optionalRoom = Optional.of(roomType);

    Mockito.when(repository.findById(roomType.getId()))
      .thenReturn(optionalRoom);
  }

  @Before
  public void setUpCreate() {
    Room roomType = new Room();
    Room found = new Room();
    found.setId(ID);
    Mockito.when(repository.save(roomType)).thenReturn(found);
  }

  @Before
  public void setUpGetAll() {
    Room roomType1 = new Room();
    Room roomType2 = new Room();
    Room roomType3 = new Room();
    List<Room> roomTypes = new ArrayList<>();
    roomTypes.add(roomType1);
    roomTypes.add(roomType2);
    roomTypes.add(roomType3);
    Mockito.when(repository.findAll()).thenReturn(roomTypes);
  }

  @Before
  public void setUpFindByInvalidId() {
    Optional<Room> empty = Optional.empty();
    Mockito.when(repository.findById(INVALIDID)).thenReturn(empty);
  }

  @Test
  public void whenValidIdRoomThenRoomShouldBeFound() {
    Room found = service.getById(ID);
    Assertions.assertThat(found.getId()).isEqualTo(ID);
  }

  @Test
  public void whenCreateRoomThenRoomShouldBeReturned() {
    Room roomType = new Room();
    Room found = service.create(roomType);
    Assertions.assertThat(found.getId()).isEqualTo(ID);
  }

  @Test
  public void whenFindAllRoomThenReturnAll() {
    List<Room> found = service.getAll();
    Assertions.assertThat(found.size()).isEqualTo(3);
  }

  @Test
  public void whenDeleteValidRoomThenReturnRoom() {
    Room found = service.delete(ID);
    Assertions.assertThat(found.getId()).isEqualTo(ID);
  }

  @Test
  public void whenDeleteInvalidRoomThenReturnNull() {
    Room found = service.delete(INVALIDID);
    Assertions.assertThat(found).isNull();
  }
}
