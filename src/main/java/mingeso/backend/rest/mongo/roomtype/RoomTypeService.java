package mingeso.backend.rest.mongo.roomtype;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class RoomTypeService {

  @Autowired
  private RoomTypeRepository repository;

  public List<RoomType> getAll() {
    return repository.findAll();
  }

  public RoomType getById(String id) {
    return repository.findById(id).orElse(null);
  }

  public RoomType create(RoomType newRoomType) {
    return repository.save(newRoomType);
  }

  public RoomType delete(String id) {
    return repository.findById(id)
      .map( room -> {
        repository.deleteById(id);
        return room;
      })
      .orElse(null);
  }

}
