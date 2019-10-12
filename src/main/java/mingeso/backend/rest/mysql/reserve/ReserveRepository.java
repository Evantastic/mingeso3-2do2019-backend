package mingeso.backend.rest.mysql.reserve;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Integer> {
  Optional<Reserve> findById(int id);
}
