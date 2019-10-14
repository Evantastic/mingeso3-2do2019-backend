package mingeso.backend.service.reserveform;

import lombok.AllArgsConstructor;
import mingeso.backend.rest.mysql.reserve.Reserve;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @PostMapping("multiple")
  public List<Reserve> makeReservations(@RequestBody List<ReserveForm> forms) {
    return service.makeReservations(forms);
  }
}
