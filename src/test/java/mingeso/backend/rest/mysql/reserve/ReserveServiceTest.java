package mingeso.backend.rest.mysql.reserve;

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

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class ReserveServiceTest {

    @TestConfiguration
    static class ReserveServiceTestContextConfiguration {
        @Bean
        public ReserveService ReserveService() { return new ReserveService(); }
    }

    @Autowired
    private ReserveService service;

    @MockBean
    private ReserveRepository repository;

    private static final int ID = 7777;
    private static final int INVALIDID = 9999;

    @Before
    public void setUpFindById() {
        Reserve reserve = new Reserve();
        reserve.setId(ID);
        Optional<Reserve> optionalReserve = Optional.of(reserve);

        Mockito.when(repository.findById(reserve.getId()))
                .thenReturn(optionalReserve);
    }

    @Before
    public void setUpCreate() {
        Reserve reserve = new Reserve();
        Reserve found = new Reserve();
        found.setId(ID);
        Mockito.when(repository.save(reserve)).thenReturn(found);
    }

    @Before
    public void setUpGetAll() {
        Reserve reserve1 = new Reserve();
        Reserve reserve2 = new Reserve();
        Reserve reserve3 = new Reserve();
        List<Reserve> reserves = new ArrayList<>();
        reserves.add(reserve1);
        reserves.add(reserve2);
        reserves.add(reserve3);
        Mockito.when(repository.findAll()).thenReturn(reserves);
    }

    @Before
    public void setUpFindByInvalidId() {
        Optional<Reserve> empty = Optional.empty();
        Mockito.when(repository.findById(INVALIDID)).thenReturn(empty);
    }

    @Test
    public void whenValidIdReserveThenReserveShouldBeFound() {
        Reserve found = service.getById(ID);
        Assertions.assertThat(found.getId()).isEqualTo(ID);
    }

    @Test
    public void whenCreateReserveThenReserveShouldBeReturned() throws FileNotFoundException, MessagingException {
        Reserve reserve = new Reserve();
        Reserve found = service.create(reserve);
        Assertions.assertThat(found.getId()).isEqualTo(ID);
    }

    @Test
    public void whenFindAllReserveThenReturnAll() {
        List<Reserve> found = service.getAll();
        Assertions.assertThat(found.size()).isEqualTo(3);
    }

    @Test
    public void whenDeleteValidReserveThenReturnReserve() {
        Reserve found = service.delete(ID);
        Assertions.assertThat(found.getId()).isEqualTo(ID);
    }

    @Test
    public void whenDeleteInvalidReserveThenReturnNull() {
        Reserve found = service.delete(INVALIDID);
        Assertions.assertThat(found).isNull();
    }
}
