package mingeso.backend.rest.mysql.contactform;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ContactFormRepository extends JpaRepository<ContactForm, Integer> {
  Optional<ContactForm> findById(int id);
}
