package mingeso.backend.rest.mongo.room;


import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rest/rooms")
@CrossOrigin(origins = "*")
public class RoomController {

  private final RoomService service;

  @GetMapping()
  public Iterable<Room> getAll() {
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
}
