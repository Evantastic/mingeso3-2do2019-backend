package mingeso.backend.service.reserveform;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import java.time.LocalDate;

public class ReserveFormTest {

    private final static LocalDate STARTDATE = LocalDate.now();
    private final static LocalDate ENDDATE = LocalDate.now();
    private final static String NAME = "Jose Perez";
    private final static LocalDate BIRTH = LocalDate.now();
    private final static String EMAIL = "joseperez@gmail.com";
    private final static String PHONE = "4567891212";
    private final static int ROOMNUMBER = 101;

    @Test
    public void WhenInitializeConstructorWithAllArguments(){
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
