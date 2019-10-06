package mingeso.backend.rest.mongo.room;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends MongoRepository<Room, ObjectId> {
  Optional<Room> findById(String id);
}