package mingeso.backend.rest.mysql.reserve;



import lombok.AllArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rest/mysql/reserves")
@CrossOrigin(origins = "*")
public class ReserveController {

    private final ReserveService reserveService;

    @GetMapping()
    public Slice<Reserve> getAll(@RequestParam(value = "page") int page, @RequestParam(value = "quantity") int quantity){
        return reserveService.getAll(page, quantity);
    }

    @GetMapping("/{id}")
    public Reserve getById(@PathVariable int id) {
        return reserveService.getById(id);
    }

    @PostMapping()
    public Reserve create(@RequestBody Reserve reserve) {
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
