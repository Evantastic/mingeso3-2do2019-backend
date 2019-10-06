package mingeso.backend.rest.mongo.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bed {

  private String type;
  private int quantity;

}
