package mingeso.backend.rest.mongo.room;

import lombok.*;
import mingeso.backend.rest.mongo.roomtype.RoomType;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("Room")
public class Room extends RoomType {

  private int roomNumber;
}
