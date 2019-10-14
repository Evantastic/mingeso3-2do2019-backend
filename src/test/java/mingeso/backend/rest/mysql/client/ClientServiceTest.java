package mingeso.backend.rest.mysql.client;

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
public class ClientServiceTest {

  @TestConfiguration
  static class ClientServiceTestContextConfiguration {
    @Bean
    public ClientService clientService() {
      return new ClientService();
    }
  }

  @Autowired
  private ClientService service;

  @MockBean
  private ClientRepository repository;

  private static final int ID = 7777;
  private static final int INVALIDID = 9999;

  @Before
  public void setUpFindById() {
    Client client = new Client();
    client.setId(ID);
    Optional<Client> optionalClient = Optional.of(client);

    Mockito.when(repository.findById(client.getId()))
      .thenReturn(optionalClient);
  }

  @Before
  public void setUpCreate() {
    Client client = new Client();
    Client found = new Client();
    found.setId(ID);
    Mockito.when(repository.save(client)).thenReturn(found);
  }

  @Before
  public void setUpGetAll() {
    Client client1 = new Client();
    Client client2 = new Client();
    Client client3 = new Client();
    List<Client> clients = new ArrayList<>();
    clients.add(client1);
    clients.add(client2);
    clients.add(client3);
    Mockito.when(repository.findAll()).thenReturn(clients);
  }

  @Before
  public void setUpFindByInvalidId() {
    Optional<Client> empty = Optional.empty();
    Mockito.when(repository.findById(INVALIDID)).thenReturn(empty);
  }

  @Test
  public void whenValidIdClientThenClientShouldBeFound() {
    Client found = service.getById(ID);
    Assertions.assertThat(found.getId()).isEqualTo(ID);
  }

  @Test
  public void whenCreateClientThenClientShouldBeReturned() {
    Client client = new Client();
    Client found = service.create(client);
    Assertions.assertThat(found.getId()).isEqualTo(ID);
  }

  @Test
  public void whenFindAllClientThenReturnAll() {
    List<Client> found = service.getAll();
    Assertions.assertThat(found.size()).isEqualTo(3);
  }

  @Test
  public void whenDeleteValidClientThenReturnClient() {
    Client found = service.delete(ID);
    Assertions.assertThat(found.getId()).isEqualTo(ID);
  }

  @Test
  public void whenDeleteInvalidClientThenReturnNull() {
    Client found = service.delete(INVALIDID);
    Assertions.assertThat(found).isNull();
  }











}
