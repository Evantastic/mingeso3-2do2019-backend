package mingeso.backend.rest.mongo.room;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {
  List<Room> findAll();
  Optional<Room> findFirstByTitle(String title);
}