package mingeso.backend.service.rack;

import mingeso.backend.rest.mysql.reserve.Reserve;
import mingeso.backend.rest.mysql.reserve.ReserveRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class RackServiceTest {
  @Mock
  private ReserveRepository reserveRepository;

  private static final LocalDate START = LocalDate.of(2020, 2, 1);
  private static final LocalDate END = LocalDate.of(2020, 2, 5);

  private List<Reserve> getValidReserves() {
    Reserve reserve1 = new Reserve();
    Reserve reserve2 = new Reserve();
    reserve1.setStartDate(LocalDate.of(2020, 2, 1));
    reserve1.setEndDate(LocalDate.of(2020, 2, 4));
    reserve2.setStartDate(LocalDate.of(2020, 2, 2));
    reserve2.setEndDate(LocalDate.of(2020, 2, 5));
    return Arrays.asList(reserve1, reserve2);
  }

  @Test
  public void whenTwoValidDatesThenReturnArray() {
    when(reserveRepository.findAllByStartDateBetweenAndEndDateBetween(START,
      END, START, END)).thenReturn(this.getValidReserves());
    assertThat(reserveRepository.findAllByStartDateBetweenAndEndDateBetween(START, END, START, END))
      .containsAll(this.getValidReserves());
  }
}
