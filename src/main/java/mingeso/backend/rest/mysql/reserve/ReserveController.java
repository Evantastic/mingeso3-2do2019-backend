package mingeso.backend.rest.mysql.reserve;



import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rest/mysql/reserves")
@CrossOrigin(origins = "*")
public class ReserveController {

    private ReserveService reserveService;

    @GetMapping()
    public List<Reserve> getAll(){
        return reserveService.getAll();
    }

    @GetMapping("/{id}")
    public Reserve getById(@PathVariable int id) {
        return reserveService.getById(id);
    }

    @PostMapping()
    public Reserve create(@RequestBody Reserve reserve) throws FileNotFoundException, MessagingException {
        return reserveService.create(reserve);
    }

    @PostMapping("/{id}")
    public Reserve update(@PathVariable int id, @RequestBody Reserve newReserve) {
        return reserveService.update(id, newReserve);
    }

    @DeleteMapping("/{id}")
    public Reserve delete(@PathVariable int id) {
        return reserveService.delete(id);
    }
}
