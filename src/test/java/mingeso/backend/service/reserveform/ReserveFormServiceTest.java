package mingeso.backend.service.reserveform;

import mingeso.backend.rest.mongo.room.Room;
import mingeso.backend.rest.mysql.client.Client;
import mingeso.backend.rest.mysql.client.ClientRepository;
import mingeso.backend.rest.mysql.reserve.Reserve;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ReserveFormServiceTest {

  @Mock
  private ClientRepository clientRepository;

  @InjectMocks
  private ReserveFormService reserveFormService;

  private static final String EMAIL = "barahonaruiz.alan@gmail.com";
  private static final int INTID = 50;

  @Test
  public void whenCreateOrFindCllientByVaildEmailThenReturnClient() {
    Client client = new Client();
    client.setEmail(EMAIL);
    client.setId(INTID);
    ReserveForm form = new ReserveForm();
    form.setEmail(EMAIL);
    when(clientRepository.findFirstByEmail(EMAIL)).thenReturn(Optional.of(client));
    Client found = reserveFormService.createOrFindClient(form);
    assertThat(found.getId()).isEqualTo(INTID);
  }

  @Test
  public void whenCreateOrFindCllientByInvalidEmailThenReturnNewClient() {
    ReserveForm form = new ReserveForm();
    form.setEmail(EMAIL);
    when(clientRepository.findFirstByEmail(EMAIL)).thenReturn(Optional.empty());
    when(clientRepository.save(any(Client.class))).thenReturn(new Client());
    Client found = reserveFormService.createOrFindClient(form);
    assertThat(found.getId()).isNotEqualTo(INTID);
  }

  @Test
  public void whenValidDatesThenReturnValidPrice() {
    ReserveForm form = new ReserveForm();
    form.setStartDate(LocalDate.of(2019,12,1));
    form.setEndDate(LocalDate.of(2019,12,5));
    Room room = new Room();
    room.setPrice(100);
    Reserve found = reserveFormService.createReserve(form, room, new Client());
    assertThat(found.getPrice()).isEqualTo(400);
  }
}
