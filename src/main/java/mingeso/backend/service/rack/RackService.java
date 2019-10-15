package mingeso.backend.service.rack;

import lombok.AllArgsConstructor;
import mingeso.backend.rest.mongo.room.Room;
import mingeso.backend.rest.mongo.room.RoomService;
import mingeso.backend.rest.mysql.client.Client;
import mingeso.backend.rest.mysql.client.ClientService;
import mingeso.backend.rest.mysql.reserve.Reserve;
import mingeso.backend.rest.mysql.reserve.ReserveRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RackService {

  private final ReserveRepository reserveRepository;
  private final ClientService clientService;
  private final RoomService roomService;
  private static final DateTimeFormatter FORMATTER =
    DateTimeFormatter.ofPattern("dd-MM-yyyy");

  protected List<Reserve> getReservesBetweenTwoDates(LocalDate start,
                                                     LocalDate end) {
    List<Reserve> reserves1 = reserveRepository.
      findAllByStartDateBetween(start, end);
    List<Reserve> reserves2 = reserveRepository.
      findAllByEndDateBetween(start, end);
    for (Reserve reserve: reserves1) {
      if (!reserves2.contains(reserve)) {
        reserves2.add(reserve);
      }
    }
    return reserves2;
  }

  public List<RackUnit> getRack(String start, String end) {
    LocalDate startDate = LocalDate.parse(start, FORMATTER);
    LocalDate endDate = LocalDate.parse(end, FORMATTER);
    ArrayList<RackUnit> rack = new ArrayList<>();
    for (Reserve reserve: this.getReservesBetweenTwoDates(startDate,
      endDate)) {
      Client client = clientService.getById(reserve.getClientId());
      Room room = roomService.getById(reserve.getRoomId());
      RackUnit rackUnit = new RackUnit.Builder()
              .setClientName(client.getName())
              .setRoomNumber(room.getRoomNumber())
              .setStartDate(reserve.getStartDate())
              .setEndDate(reserve.getEndDate())
              .build();

      rack.add(rackUnit);
    }

    return rack;
  }
}
