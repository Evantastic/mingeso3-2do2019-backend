package mingeso.backend.rest.mysql.client;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Integer>{
    Optional<Client> findById(int id);
}
