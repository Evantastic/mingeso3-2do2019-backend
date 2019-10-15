package mingeso.backend.rest.mysql.contactform;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactFormController.class)
@AutoConfigureDataJpa
public class ContactFormControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private ContactFormService service;

  @Autowired
  private ObjectMapper mapper;

  private static final int ID = 7777;
  private static final String NAME = "jose perez cotapo";
  private static final String EMAIL = "jose@gmail.com";
  private static final String PHONE = "3456789";
  private static final String DETAILS = "HERMOSO HOTEL";
  private static final String URL = "/api/rest/mysql/contactForms";

  @Test
  public void givenContactFormWhenGetContactFormsThenReturnJsonArray(){
    ContactForm contactForm = new ContactForm();
    List<ContactForm> allContactForms = new ArrayList<>();
    contactForm.setName(NAME);
    allContactForms.add(contactForm);
    given(service.getAll()).willReturn(allContactForms);
    try {
      mvc.perform(get(URL)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$",
          hasSize(1)))
        .andExpect(jsonPath("$[0].name",
          is(NAME)));
    } catch (Exception ignored) {
    }
  }

  @Test
  public void givenIdWhenGetContactFormByIdThenReturnJson(){
    ContactForm contactForm = new ContactForm();
    contactForm.setId(ID);
    given(service.getById(ID)).willReturn(contactForm);
    try {
      mvc.perform(get(URL + "/" + ID)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id",
          is(ID)));
    } catch (Exception ignored) {
    }
  }

  @Test
  public void whenCreateContactFormThenReturnJson(){
    ContactForm contactForm = new ContactForm();
    contactForm.setId(ID);
    contactForm.setName(NAME);
    contactForm.setEmail(EMAIL);
    contactForm.setPhone(PHONE);
    contactForm.setDetails(DETAILS);
    given(service.create(contactForm)).willReturn(contactForm);
    try {
      mvc.perform(post(URL)
        .contentType(APPLICATION_JSON)
        .content(mapper.writeValueAsString(contactForm)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id",
          is(contactForm.getId())))
        .andExpect(jsonPath("$.name",
          is(contactForm.getName())))
        .andExpect(jsonPath("$.email",
          is(contactForm.getEmail())))
        .andExpect(jsonPath("$.phone",
          is(contactForm.getPhone())))
        .andExpect(jsonPath("$.details",
          is(contactForm.getDetails())));
    } catch (Exception ignored) {
    }
  }

  @Test
  public void whenUpdateContactFormThenReturnJson(){
    ContactForm contactForm = new ContactForm();
    contactForm.setId(ID);
    contactForm.setName(NAME);
    contactForm.setEmail(EMAIL);
    contactForm.setPhone(PHONE);
    contactForm.setDetails(DETAILS);
    given(service.update(ID,contactForm)).willReturn(contactForm);
    try {
      mvc.perform(post(URL+"/"+ID)
              .contentType(APPLICATION_JSON)
              .content(mapper.writeValueAsString(contactForm)))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.id",
                      is(contactForm.getId())))
              .andExpect(jsonPath("$.name",
                      is(contactForm.getName())))
              .andExpect(jsonPath("$.email",
                      is(contactForm.getEmail())))
              .andExpect(jsonPath("$.phone",
                      is(contactForm.getPhone())))
              .andExpect(jsonPath("$.details",
                      is(contactForm.getDetails())));
    } catch (Exception ignored) {
    }
  }

  @Test
  public void whenDeleteContactFormThenReturnJson(){
    ContactForm contactForm = new ContactForm();
    contactForm.setId(ID);
    contactForm.setName(NAME);
    given(service.delete(contactForm.getId())).willReturn(contactForm);
    try {
      mvc.perform(delete(URL + "/" + ID)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name",
          is(contactForm.getName())));
    } catch (Exception ignored) {
    }
  }
}

