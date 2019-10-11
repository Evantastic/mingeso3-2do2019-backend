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

  private static final String TITLE1 = "type1";
  private static final String TITLE2 = "type2";

  @Before
  public void setUpGetTypesOfRoom() {
    List<Room> found = new ArrayList<>();
    Room room1 = new Room();
    room1.setTitle(TITLE1);
    Room room2 = new Room();
    room2.setTitle(TITLE2);
    Room room3 = new Room();
    room3.setTitle(TITLE2);
    found.add(room1);
    found.add(room2);
    found.add(room3);
    Mockito.when(repository.findAll()).thenReturn(found);
  }

  @Test
  public void whenGetTitleOfRoomReturnListOfStrings() {
    List<Room> found = service.getTitlesOfRoom();
    List<Room> titles = new ArrayList<>();
    Room room1 = new Room();
    Room room2 = new Room();
    room1.setTitle(TITLE1);
    room2.setTitle(TITLE2);
    titles.add(room1);
    titles.add(room2);
    Assertions.assertThat(found)
      .containsAll(titles)
      .doesNotHaveDuplicates();
  }
}
