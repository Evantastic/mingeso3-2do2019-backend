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

  private static boolean hasTitle(List<Room> rooms, String title) {
    for (Room room: rooms) {
      if (room.getTitle().equalsIgnoreCase(title)) {
        return true;
      }
    }
    return false;
  }

  public List<Room> getTitlesOfRoom() {
    List<Room> rooms = new ArrayList<>();
    for (Room room : repository.findAll()) {
      if (hasTitle(rooms, room.getTitle())) {
        continue;
      }
      rooms.add(room);
    }
    return rooms;
  }

}
