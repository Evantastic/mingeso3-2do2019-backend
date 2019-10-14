package mingeso.backend.service.choiceroom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ChoiceRoomTest {

  private static final int NUMBER = 50;
  private static final String TITLE = "title";

  @Test
  public void getterAndSetterAndConstructors() {
    ChoiceRoom room1 = new ChoiceRoom(NUMBER, TITLE);
    ChoiceRoom room2 = new ChoiceRoom();
    room2.setRoomNumber(NUMBER);
    room2.setRoomTitle(TITLE);
    assertThat(room1).isEqualToComparingFieldByField(room2);
  }
}
