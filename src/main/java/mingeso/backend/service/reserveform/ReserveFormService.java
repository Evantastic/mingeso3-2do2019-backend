package mingeso.backend.service.reserveform;

import mingeso.backend.rest.mongo.room.Room;
import mingeso.backend.rest.mongo.room.RoomRepository;
import mingeso.backend.rest.mysql.client.Client;
import mingeso.backend.rest.mysql.client.ClientRepository;
import mingeso.backend.rest.mysql.reserve.Reserve;
import mingeso.backend.rest.mysql.reserve.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReserveFormService {

  @Autowired
  private ClientRepository clientRepository;
  @Autowired
  private RoomRepository roomRepository;
  @Autowired
  private ReserveService reserveService;

  protected Client createOrFindClient(ReserveForm form) {
    Client client = new Client(0, form.getName(), form.getBirth(),
      form.getEmail(), form.getPhone());
    return clientRepository.findFirstByEmail(form.getEmail())
      .orElse(clientRepository.save(client));
  }

  protected Room getByRoomNumber(int roomNumber) {
    return roomRepository.findFirstByRoomNumber(roomNumber).orElse(null);
  }

  protected Reserve createReserve(ReserveForm form, Room room, Client client) {
    int days =
      Period.between(form.getStartDate(), form.getEndDate()).getDays();
    return new Reserve(0, client.getId(), room.getId(),
      form.getStartDate(), form.getEndDate(), room.getPrice()*days);
  }

  public Reserve makeReservation(ReserveForm form) {
    Room room = this.getByRoomNumber(form.getRoomNumber());
    if (room == null) {
      return null;
    }
    Client client = this.createOrFindClient(form);
    Reserve reserve = this.createReserve(form, room, client);
    return reserveService.create(reserve);
  }

  public List<Reserve> makeReservations(List<ReserveForm> forms) {
    List<Reserve> reserves = new ArrayList<>();
    for (ReserveForm form: forms) {
      reserves.add(this.makeReservation(form));
    }
    return reserves;
  }
}
