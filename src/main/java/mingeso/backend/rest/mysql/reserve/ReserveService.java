package mingeso.backend.rest.mysql.reserve;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.util.List;

@Service
@NoArgsConstructor
public class ReserveService {

  @Autowired
  private ReserveRepository reserveRepository;


  public List<Reserve> getAll() {
    return reserveRepository.findAll();
  }

  public Reserve getById(int id) {
    return reserveRepository.findById(id)
      .orElse(null);
  }

  public Reserve create(Reserve reserve){
    return reserveRepository.save(reserve);
  }

  public Reserve update(int id, Reserve newReserve) {
    return reserveRepository.findById(id)
      .map(contactForm -> {
        contactForm.setFromReserve(newReserve);
        return reserveRepository.save(contactForm);
      })
      .orElse(null);
  }

  public Reserve delete(int id) {
    return reserveRepository.findById(id)
      .map(contactForm -> {
        reserveRepository.delete(contactForm);
        return contactForm;
      })
      .orElse(null);
  }
}
