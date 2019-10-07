package mingeso.backend.rest.mysql.reserve;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Reserve")
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "clientid", unique = false, nullable = false)
    private int clientId;

    @Column(name = "roomid", unique = false, nullable = false)
    private int roomid;

    @Column(name = "starDate")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @Column(name = "endDate")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    @Column(name="price")
    private int price;




}
