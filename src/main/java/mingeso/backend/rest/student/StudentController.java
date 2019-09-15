package mingeso.backend.rest.student;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rest/students")
public class StudentController{

    private final StudentService studentService;

	@GetMapping()
	public Slice<Student> getAll(@RequestParam(value = "page") int page, @RequestParam(value = "quantity") int quantity){
		return studentService.getAll(page, quantity);
	}
	
	@GetMapping("/{id}")
	public Student getById(@PathVariable int id) {
	    return studentService.getById(id);
	}
	
	@PostMapping()
	public Student create(@RequestBody Student student) {
	    return studentService.create(student);
	}
	
	@PostMapping("/{id}")
	public Student update(@PathVariable int id, @RequestBody Student newStudent) {
	    return studentService.update(id, newStudent);
	}
	
	@DeleteMapping("/{id}")
	public Student delete(@PathVariable int id) {
	    return studentService.delete(id);
	}
}
