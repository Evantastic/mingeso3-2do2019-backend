package mingeso.backend.service.rack;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RackUnit {

  private String clientName;
  private int roomNumber;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate startDate;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate endDate;


  public static class Builder {

    private String clientName;
    private int roomNumber;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;


    public Builder setClientName(String clientName){
      this.clientName = clientName;
      return this;
    }

    public Builder setRoomNumber(int roomNumber){
      this.roomNumber = roomNumber;
      return this;
    }

    public Builder setStartDate(LocalDate startDate){
      this.startDate = startDate;
      return this;
    }

    public Builder setEndDate(LocalDate endDate){
      this.endDate = endDate;
      return this;
    }

    public RackUnit build() {

      RackUnit rackUnit = new RackUnit();
      rackUnit.clientName = this.clientName;
      rackUnit.roomNumber = this.roomNumber;
      rackUnit.startDate = this.startDate;
      rackUnit.endDate = this.endDate;

      return rackUnit;
    }
  }
  private RackUnit(){ }
}
