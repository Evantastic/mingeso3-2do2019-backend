package mingeso.backend.rest.mysql.reserve;



import lombok.AllArgsConstructor;
import mingeso.backend.service.mail.EmailService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;


@Service
@AllArgsConstructor
public class ReserveService {

    private ReserveRepository reserveRepository;
    private EmailService emailService;


    public Slice<Reserve> getAll(int page, int quantity) {
        return reserveRepository.findAll(PageRequest.of(page, quantity));
    }

    public Reserve getById(int id) {
        return reserveRepository.findById(id)
                .orElse(null);
    }

    public Reserve create(Reserve reserve) throws FileNotFoundException, MessagingException {
        emailService.sendSimpleMessage("gabogsc@gmail.com", "FUNCIONO CTM", "Sopla la diuca");
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
