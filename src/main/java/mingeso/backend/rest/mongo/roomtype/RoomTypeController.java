package mingeso.backend.rest.mongo.roomtype;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rest/mongo/roomtypes")
@CrossOrigin(origins = "*")
public class RoomTypeController {

  private final RoomTypeService service;

  @GetMapping()
  public List<RoomType> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public RoomType getById(@PathVariable String id) {
    return service.getById(id);
  }

  @PostMapping()
  public RoomType create(@RequestBody RoomType newRoomType) {
    return service.create(newRoomType);
  }

  @DeleteMapping("/{id}")
  public RoomType delete(@PathVariable String id) {return service.delete(id);}
}
