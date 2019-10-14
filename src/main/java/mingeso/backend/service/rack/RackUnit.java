package mingeso.backend.service.rack;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RackUnit {
  private String clientName;
  private int roomNumber;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate startDate;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate endDate;
}
