package mingeso.backend.service.choiceroom;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/services/room/choice")
@CrossOrigin(origins = "*")
public class ChoiceRoomRepository {

  private final ChoiceRoomService service;

  @GetMapping
  public List<ChoiceRoom> getAll() {
    return service.getAll();
  }
}
