package mingeso.backend.service.reserveForm;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
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
