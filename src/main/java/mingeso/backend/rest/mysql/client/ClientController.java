package mingeso.backend.rest.mysql.client;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rest/mysql/clients")
@CrossOrigin(origins = "*")
public class ClientController {

    private final ClientService clientService;

    @GetMapping()
    public Slice<Client> getAll(@RequestParam(value = "page") int page, @RequestParam(value = "quantity") int quantity){
        return clientService.getAll(page, quantity);
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable int id) {
        return clientService.getById(id);
    }

    @PostMapping()
    public Client create(@RequestBody Client contactForm) {
        return clientService.create(contactForm);
    }

    @PostMapping("/{id}")
    public Client update(@PathVariable int id, @RequestBody Client newClient) {
        return clientService.update(id, newClient);
    }

    @DeleteMapping("/{id}")
    public Client delete(@PathVariable int id) {
        return clientService.delete(id);
    }
}
