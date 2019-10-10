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

  @Id
  private String id;

  private String url;
  private String title;
  private int price;
  private String description;
  private int beds;
  private int capacity;
  private List<String> services;

}
