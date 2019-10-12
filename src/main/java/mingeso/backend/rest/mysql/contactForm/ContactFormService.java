package mingeso.backend.rest.mysql.contactForm;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@NoArgsConstructor
public class ContactFormService {

  @Autowired
  private ContactFormRepository contactFormRepository;


  public List<ContactForm> getAll() {
    return contactFormRepository.findAll();
  }

  public ContactForm getById(int id) {
    return contactFormRepository.findById(id)
      .orElse(null);
  }

  public ContactForm create(ContactForm contactForm) {
    return contactFormRepository.save(contactForm);
  }

  public ContactForm update(int id, ContactForm newContactForm) {
    return contactFormRepository.findById(id)
      .map(contactForm -> {
        contactForm.setFromContactForm(newContactForm);
        return contactFormRepository.save(contactForm);
      })
      .orElse(null);
  }

  public ContactForm delete(int id) {
    return contactFormRepository.findById(id)
      .map(contactForm -> {
        contactFormRepository.delete(contactForm);
        return contactForm;
      })
      .orElse(null);
  }
}
