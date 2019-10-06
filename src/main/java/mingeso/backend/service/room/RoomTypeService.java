package mingeso.backend.service.room;

import lombok.NoArgsConstructor;
import mingeso.backend.rest.mongo.room.Room;
import mingeso.backend.rest.mongo.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class RoomTypeService {

  @Autowired
  private RoomRepository repository;

  public List<String> getTypesOfRoom() {
    List<Room> rooms = repository.findAll();
    List<String> types = new ArrayList<>();
    for (Room room: rooms) {
      if (types.contains(room.getType())) {
        continue;
      }
      types.add(room.getType());
    }
    return types;
  }

  public Room getRoomByType(String type) {
    return repository.findFirstByType(type).orElse(null);
  }

}
