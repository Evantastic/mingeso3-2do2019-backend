package mingeso.backend.rest.mysql.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Client")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "birth")
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate birth;

  @Column(name="email")
  private String email;

  @Column(name="phone")
  private String phone;

  public void setFromClient(Client newClient){
    this.name = newClient.getName();
    this.birth = newClient.getBirth();
    this.email = newClient.getEmail();
    this.phone = newClient.getPhone();
  }
}
