package mingeso.backend.rest.student;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Slice<Student> getAll(int page, int quantity) {
        return studentRepository.findAll(PageRequest.of(page, quantity));
    }

    public Student getById(int id) {
        return studentRepository.findById(id)
                .orElse(null);
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student update(int id, Student newStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setFromStudent(newStudent);
                    return studentRepository.save(student);
                })
                .orElse(null);
    }

    public Student delete(int id) {
        return studentRepository.findById(id)
                .map(student -> {
                    studentRepository.delete(student);
                    return student;
                })
                .orElse(null);
    }
}
