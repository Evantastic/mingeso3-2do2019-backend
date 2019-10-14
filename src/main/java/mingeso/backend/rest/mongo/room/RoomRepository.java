package mingeso.backend.rest.mongo.room;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {
  Optional<Room> findFirstByRoomNumber(int roomNumber);
}
