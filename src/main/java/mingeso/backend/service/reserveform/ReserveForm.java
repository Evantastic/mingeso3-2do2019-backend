package mingeso.backend.service.reserveform;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReserveForm {

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate startDate;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate endDate;
  private String name;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate birth;
  private String email;
  private String phone;
  private String roomId;
}
