package mingeso.backend.rest.mongo.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("Room")
public class Room {

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class Extra {
    private String type;
    private int quantity;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class Bed {
    private String type;
    private int quantity;
  }

  @Id
  private String id;

  private int price;
  private int capacity;
  private int floor;
  private int roomNumber;
  private List<Extra> extras;
  private List<Bed> beds;
  private List<String> services;
  private String view;
  private String description;
  private String type;

}
