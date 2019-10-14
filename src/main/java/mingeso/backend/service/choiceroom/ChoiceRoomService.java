package mingeso.backend.service.choiceroom;

import lombok.AllArgsConstructor;
import mingeso.backend.rest.mongo.room.Room;
import mingeso.backend.rest.mongo.room.RoomService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChoiceRoomService {

  private final RoomService service;

  public List<ChoiceRoom> getAll() {
    List<ChoiceRoom> choiceRooms = new ArrayList<>();
    for (Room room: service.getAll()) {
      choiceRooms.add(new ChoiceRoom(room.getRoomNumber(), room.getTitle()));
    }
    return choiceRooms;
  }
}
