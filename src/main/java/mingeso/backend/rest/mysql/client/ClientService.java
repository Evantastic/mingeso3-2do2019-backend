package mingeso.backend.rest.mysql.client;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;


    public Slice<Client> getAll(int page, int quantity) {
        return clientRepository.findAll(PageRequest.of(page, quantity));
    }

    public Client getById(int id) {
        return clientRepository.findById(id)
                .orElse(null);
    }

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public Client update(int id, Client newClient) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setFromClient(newClient);
                    return clientRepository.save(client);
                })
                .orElse(null);
    }

    public Client delete(int id) {
        return clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return client;
                })
                .orElse(null);
    }
}
