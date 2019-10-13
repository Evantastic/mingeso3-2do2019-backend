package mingeso.backend.rest.mysql.contactform;


import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class ContactFormServiceTest {

  @TestConfiguration
  static class ContactFormServiceTestContextConfiguration {
    @Bean
    public ContactFormService contactFormService() {
      return new ContactFormService();
    }
  }


  @Autowired
  private ContactFormService service;

  @MockBean
  private ContactFormRepository repository;

  private static final int ID = 7777;
  private static final int INVALIDID = 9999;

  @Before
  public void setUpFindById() {
    ContactForm contactForm = new ContactForm();
    contactForm.setId(ID);
    Optional<ContactForm> optionalContactForm = Optional.of(contactForm);

    Mockito.when(repository.findById(contactForm.getId()))
      .thenReturn(optionalContactForm);
  }

  @Before
  public void setUpCreate() {
    ContactForm contactForm = new ContactForm();
    ContactForm found = new ContactForm();
    found.setId(ID);
    Mockito.when(repository.save(contactForm)).thenReturn(found);
  }

  @Before
  public void setUpGetAll() {
    ContactForm contactForm1 = new ContactForm();
    ContactForm contactForm2 = new ContactForm();
    ContactForm contactForm3 = new ContactForm();
    List<ContactForm> contactForms = new ArrayList<>();
    contactForms.add(contactForm1);
    contactForms.add(contactForm2);
    contactForms.add(contactForm3);
    Mockito.when(repository.findAll()).thenReturn(contactForms);
  }

  @Before
  public void setUpFindByInvalidId() {
    Optional<ContactForm> empty = Optional.empty();
    Mockito.when(repository.findById(INVALIDID)).thenReturn(empty);
  }

  @Test
  public void whenValidIdContactFormThenContactFormShouldBeFound() {
    ContactForm found = service.getById(ID);
    Assertions.assertThat(found.getId()).isEqualTo(ID);
  }

  @Test
  public void whenCreateContactFormThenContactFormShouldBeReturned() {
    ContactForm contactForm = new ContactForm();
    ContactForm found = service.create(contactForm);
    Assertions.assertThat(found.getId()).isEqualTo(ID);
  }

  @Test
  public void whenFindAllContactFormThenReturnAll() {
    List<ContactForm> found = service.getAll();
    Assertions.assertThat(found.size()).isEqualTo(3);
  }

  @Test
  public void whenDeleteValidContactFormThenReturnContactForm() {
    ContactForm found = service.delete(ID);
    Assertions.assertThat(found.getId()).isEqualTo(ID);
  }

  @Test
  public void whenDeleteInvalidContactFormThenReturnNull() {
    ContactForm found = service.delete(INVALIDID);
    Assertions.assertThat(found).isNull();
  }
}
