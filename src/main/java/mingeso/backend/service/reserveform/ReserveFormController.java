package mingeso.backend.service.reserveform;

import lombok.AllArgsConstructor;
import mingeso.backend.rest.mysql.reserve.Reserve;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/services/reserves/form")
@CrossOrigin(origins = "*")
public class ReserveFormController {

  private final ReserveFormService service;

  @PostMapping()
  public Reserve makeReservation(@RequestBody ReserveForm form) {
      return service.makeReservation(form);
  }
}
