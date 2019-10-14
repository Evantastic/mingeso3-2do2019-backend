package mingeso.backend.service.choiceroom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ChoiceRoomTest {

  private static final int NUMBER = 50;
  private static final String TITLE = "title";

  @Test
  public void getterAndSetterAndConstructors() {
    ChoiceRoom room1 = new ChoiceRoom(Arrays.asList(NUMBER, NUMBER), TITLE);
    ChoiceRoom room2 = new ChoiceRoom();
    room2.setRooms(Arrays.asList(NUMBER, NUMBER));
    room2.setRoomTitle(TITLE);
    assertThat(room1).isEqualToComparingFieldByField(room2);
  }
}
