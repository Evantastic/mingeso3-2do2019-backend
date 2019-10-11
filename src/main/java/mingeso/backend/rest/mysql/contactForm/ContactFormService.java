package mingeso.backend.rest.mysql.contactForm;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactFormService {

    private final ContactFormRepository contactFormRepository;


    public Slice<ContactForm> getAll(int page, int quantity) {
        return contactFormRepository.findAll(PageRequest.of(page, quantity));
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
