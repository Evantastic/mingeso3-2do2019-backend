package mingeso.backend.service.room;

import lombok.AllArgsConstructor;
import mingeso.backend.rest.mongo.room.Room;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/service/rooms/types")
@CrossOrigin(origins = "*")
public class RoomTypeController {

  private final RoomTypeService service;

  @GetMapping()
  public List<Room> getTypesOfRoom() {
    return service.getTitlesOfRoom();
  }
}
