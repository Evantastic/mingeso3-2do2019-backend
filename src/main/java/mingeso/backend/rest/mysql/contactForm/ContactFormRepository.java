package mingeso.backend.rest.mysql.contactForm;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactFormRepository extends PagingAndSortingRepository<ContactForm, Integer> {
    Optional<ContactForm> findById(int id);
}
