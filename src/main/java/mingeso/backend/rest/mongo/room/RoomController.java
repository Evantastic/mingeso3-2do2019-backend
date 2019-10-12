package mingeso.backend.rest.mongo.room;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rest/mongo/rooms")
@CrossOrigin(origins = "*")
public class RoomController {

  private final RoomService service;

  @GetMapping()
  public List<Room> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public Room getById(@PathVariable String id) {
    return service.getById(id);
  }

  @PostMapping()
  public Room create(@RequestBody Room newRoom) {
    return service.create(newRoom);
  }

  @DeleteMapping("/{id}")
  public Room delete(@PathVariable String id) {return service.delete(id);}

}
