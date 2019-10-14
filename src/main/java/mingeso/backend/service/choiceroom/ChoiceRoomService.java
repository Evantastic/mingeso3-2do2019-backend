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

  protected void addRoomNumberToCorrespondingTitle(List<ChoiceRoom> rooms,
                                                   Room room) {
    for (ChoiceRoom choiceRoom: rooms) {
      if (choiceRoom.getRoomTitle().equalsIgnoreCase(room.getTitle())) {
        choiceRoom.getRooms().add(room.getRoomNumber());
        return;
      }
    }
    List<Integer> roomNumbers = new ArrayList<>();
    roomNumbers.add(room.getRoomNumber());
    rooms.add(new ChoiceRoom(roomNumbers, room.getTitle()));
  }

  public List<ChoiceRoom> getAll() {
    List<ChoiceRoom> rooms = new ArrayList<>();
    for (Room room: service.getAll()) {
      this.addRoomNumberToCorrespondingTitle(rooms, room);
    }
    return rooms;
  }
}
