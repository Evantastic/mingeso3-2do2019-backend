package mingeso.backend.rest.mysql.contactForm;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rest/mysql/contactForms")
@CrossOrigin(origins = "*")
public class ContactFormController {

  private final ContactFormService contactFormService;

  @GetMapping()
  public List<ContactForm> getAll(){
    return contactFormService.getAll();
  }

  @GetMapping("/{id}")
  public ContactForm getById(@PathVariable int id) {
    return contactFormService.getById(id);
  }

  @PostMapping()
  public ContactForm create(@RequestBody ContactForm contactForm) {
    return contactFormService.create(contactForm);
  }

  @PostMapping("/{id}")
  public ContactForm update(@PathVariable int id, @RequestBody ContactForm newContactForm) {
    return contactFormService.update(id, newContactForm);
  }

  @DeleteMapping("/{id}")
  public ContactForm delete(@PathVariable int id) {
    return contactFormService.delete(id);
  }
}
