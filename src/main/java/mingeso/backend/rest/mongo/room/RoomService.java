package mingeso.backend.rest.mongo.room;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class RoomService {

  @Autowired
  private RoomRepository repository;

  public Iterable<Room> getAll() {
    return repository.findAll();
  }

  public Room getById(String id) {
    return repository.findById(id).orElse(null);
  }

  public Room create(Room newRoom) {
    return repository.save(newRoom);
  }

}
