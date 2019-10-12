package mingeso.backend.rest.mysql.client;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.findAll();
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
