package mingeso.backend.rest.mongo.roomtype;

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
  private RoomTypeRepository repository;

  private static final String ID = "5d9a411d7d365f68746fc3d3";
  private static final String INVALIDID = "5d9a411d7d365f68746fc315";

  @Before
  public void setUpFindById() {
    RoomType roomType = new RoomType();
    roomType.setId(ID);
    Optional<RoomType> optionalRoom = Optional.of(roomType);

    Mockito.when(repository.findById(roomType.getId()))
      .thenReturn(optionalRoom);
  }

  @Before
  public void setUpCreate() {
    RoomType roomType = new RoomType();
    RoomType found = new RoomType();
    found.setId(ID);
    Mockito.when(repository.save(roomType)).thenReturn(found);
  }

  @Before
  public void setUpGetAll() {
    RoomType roomType1 = new RoomType();
    RoomType roomType2 = new RoomType();
    RoomType roomType3 = new RoomType();
    List<RoomType> roomTypes = new ArrayList<>();
    roomTypes.add(roomType1);
    roomTypes.add(roomType2);
    roomTypes.add(roomType3);
    Mockito.when(repository.findAll()).thenReturn(roomTypes);
  }
  @Before
  public void setUpFindByInvalidId() {
    Optional<RoomType> empty = Optional.empty();
    Mockito.when(repository.findById(INVALIDID)).thenReturn(empty);
  }

  @Test
  public void whenValidIdRoomTypeThenRoomShouldBeFound() {
    RoomType found = service.getById(ID);
    Assertions.assertThat(found.getId()).isEqualTo(ID);
  }

  @Test
  public void whenCreateRoomTypeThenRoomShouldBeReturned() {
    RoomType roomType = new RoomType();
    RoomType found = service.create(roomType);
    Assertions.assertThat(found.getId()).isEqualTo(ID);
  }

  @Test
  public void whenFindAllRoomTypeThenReturnAll() {
    List<RoomType> found = service.getAll();
    Assertions.assertThat(found.size()).isEqualTo(3);
  }

  @Test
  public void whenDeleteValidRoomThenReturnRoom() {
    RoomType found = service.delete(ID);
    Assertions.assertThat(found.getId()).isEqualTo(ID);
  }

  @Test
  public void whenDeleteInvalidRoomThenReturnNull() {
    RoomType found = service.delete(INVALIDID);
    Assertions.assertThat(found).isNull();
  }
}
