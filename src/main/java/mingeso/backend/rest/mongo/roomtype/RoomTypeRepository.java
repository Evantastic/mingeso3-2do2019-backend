package mingeso.backend.rest.mongo.roomtype;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomTypeRepository extends MongoRepository<RoomType, String> {
  List<RoomType> findAll();
}