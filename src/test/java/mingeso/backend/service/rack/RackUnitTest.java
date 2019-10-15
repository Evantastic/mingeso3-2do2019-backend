package mingeso.backend.service.rack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class RackUnitTest {

  private static final String NAME = "name";
  private static final int NUMBER = 50;
  private static final LocalDate NOW = LocalDate.now();

  @Test
  public void getterAndSetterAndConstructors() {
    RackUnit rack1 = new RackUnit.Builder()
            .setClientName(NAME)
            .setRoomNumber(NUMBER)
            .setStartDate(NOW)
            .setEndDate(NOW)
            .build();
    RackUnit rack2 = new RackUnit.Builder().build();
    rack2.setClientName(NAME);
    rack2.setEndDate(NOW);
    rack2.setStartDate(NOW);
    rack2.setRoomNumber(NUMBER);
    assertThat(rack1).isEqualToComparingFieldByField(rack2);
  }
}
