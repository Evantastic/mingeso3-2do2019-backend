package mingeso.backend.rest.mysql.reserve;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Integer> {
  List<Reserve> findAllByStartDateBetween(LocalDate start, LocalDate end);
  List<Reserve> findAllByEndDateBetween(LocalDate start, LocalDate end);
}
