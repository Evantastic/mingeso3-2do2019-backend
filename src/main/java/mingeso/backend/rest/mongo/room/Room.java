package mingeso.backend.rest.mongo.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mingeso.backend.rest.mongo.roomtype.RoomType;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room extends RoomType {

  private int roomNumber;
}
