package mingeso.backend.rest.mysql.reserve;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Reserve")
public class Reserve {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private int id;

  @Column(name = "clientid", nullable = false)
  private int clientId;

  @Column(name = "roomid", nullable = false)
  private String roomId;

  @Column(name = "starDate")
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate startDate;

  @Column(name = "endDate")
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate endDate;

  @Column(name="price")
  private int price;

  public void setFromReserve(Reserve newReserve){

    this.clientId = newReserve.getClientId();
    this.roomId = newReserve.getRoomId();
    this.startDate = newReserve.getStartDate();
    this.endDate = newReserve.getEndDate();
    this.price = newReserve.getPrice();
  }
}
