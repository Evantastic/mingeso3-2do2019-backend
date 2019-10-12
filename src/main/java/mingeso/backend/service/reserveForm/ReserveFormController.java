package mingeso.backend.service.reserveForm;

import lombok.AllArgsConstructor;
import mingeso.backend.rest.mysql.reserve.Reserve;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/services/reserves/form")
@CrossOrigin(origins = "*")
public class ReserveFormController {

  private final ReserveFormService service;

  @PostMapping()
  public Reserve makeReservation(@RequestBody ReserveForm form) {
    try {
      return service.makeReservation(form);
    } catch (FileNotFoundException | MessagingException e) {
      e.printStackTrace();
    }
    return null;
  }
}
