package mingeso.backend.rest.mysql.contactform;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ContactForm")
public class ContactForm {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name="email")
  private String email;

  @Column(name="phone")
  private String phone;

  @Column(name="details")
  private String details;

  public void setFromContactForm(ContactForm newContactForm){
    this.name = newContactForm.getName();
    this.email = newContactForm.getEmail();
    this.phone = newContactForm.getPhone();
    this.details = newContactForm.getDetails();
  }
}
