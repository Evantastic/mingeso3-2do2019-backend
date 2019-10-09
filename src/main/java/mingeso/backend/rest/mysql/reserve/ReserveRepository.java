package mingeso.backend.rest.mysql.reserve;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReserveRepository extends PagingAndSortingRepository<Reserve, Integer> {
    Optional<Reserve> findById(int id);
}
