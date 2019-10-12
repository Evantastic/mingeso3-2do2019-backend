package mingeso.backend.rest.mongo.roomtype;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("RoomType")
public class RoomType {

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
