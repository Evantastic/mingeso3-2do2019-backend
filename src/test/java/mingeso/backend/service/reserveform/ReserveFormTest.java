package mingeso.backend.service.reserveform;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import java.time.LocalDate;

public class ReserveFormTest {

    private static final LocalDate STARTDATE = LocalDate.now();
    private static final LocalDate ENDDATE = LocalDate.now();
    private static final String NAME = "Jose Perez";
    private static final LocalDate BIRTH = LocalDate.now();
    private static final String EMAIL = "joseperez@gmail.com";
    private static final String PHONE = "4567891212";
    private static final int ROOMNUMBER = 101;

    @Test
    public void whenInitializeConstructorWithAllArguments(){
        ReserveForm reserveForm = new ReserveForm(
                STARTDATE,
                ENDDATE,
                NAME,
                BIRTH,
                EMAIL,
                PHONE,
                ROOMNUMBER);
        assertThat(reserveForm.getStartDate()).isEqualTo(STARTDATE);
        assertThat(reserveForm.getEndDate()).isEqualTo(ENDDATE);
        assertThat(reserveForm.getName()).isEqualTo(NAME);
        assertThat(reserveForm.getBirth()).isEqualTo(BIRTH);
        assertThat(reserveForm.getEmail()).isEqualTo(EMAIL);
        assertThat(reserveForm.getPhone()).isEqualTo(PHONE);
        assertThat(reserveForm.getRoomNumber()).isEqualTo(ROOMNUMBER);
    }
}
