package mingeso.backend.service.rack;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/services/rack")
@CrossOrigin(origins = "*")
public class RackController {

  private final RackService service;

  @GetMapping()
  public List<RackUnit> getRack(@RequestParam("start") @JsonFormat(pattern =
    "dd-MM-yyyy") String start, @RequestParam("end") @JsonFormat(pattern =
    "dd-MM-yyyy") String end) {
    return service.getRack(start, end);
  }
}
