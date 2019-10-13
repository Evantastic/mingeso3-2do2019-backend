package mingeso.backend.service.reserveform;

import mingeso.backend.rest.mongo.room.Room;
import mingeso.backend.rest.mongo.room.RoomService;
import mingeso.backend.rest.mysql.client.Client;
import mingeso.backend.rest.mysql.client.ClientRepository;
import mingeso.backend.rest.mysql.reserve.Reserve;
import mingeso.backend.rest.mysql.reserve.ReserveService;
import mingeso.backend.service.mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Period;

@Service
public class ReserveFormService {

  @Autowired
  private ClientRepository clientRepository;
  @Autowired
  private RoomService roomService;
  @Autowired
  private EmailService emailService;
  @Autowired
  private ReserveService reserveService;

  protected Client createOrFindClient(ReserveForm form) {
    Client client = new Client(0, form.getName(), form.getBirth(),
      form.getEmail(), form.getPhone());
    return clientRepository.findByEmail(form.getEmail())
      .orElse(clientRepository.save(client));
  }

  protected Reserve createReserve(ReserveForm form, Room room, Client client) {
    int days =
      Period.between(form.getStartDate(), form.getEndDate()).getDays();
    return new Reserve(0, client.getId(), room.getId(),
      form.getStartDate(), form.getEndDate(), room.getPrice()*days);
  }

  public Reserve makeReservation(ReserveForm form) {
    Room room = roomService.getById(form.getRoomId());
    if (room == null) {
      return null;
    }
    Client client = this.createOrFindClient(form);
    Reserve reserve = this.createReserve(form, room, client);
    emailService.sendEmailWithTemplate(client.getEmail(),
      Integer.toString(reserve.getId()), client.getName());
    return reserveService.create(reserve);
  }
}
