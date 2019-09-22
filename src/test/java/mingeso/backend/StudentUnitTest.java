package mingeso.backend;

import mingeso.backend.rest.student.Student;
import mingeso.backend.rest.student.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    private Student studentMockUp(){
        Student nicole = new Student();
        nicole.setBirth(LocalDate.of(1995,06,14));
        nicole.setCareer("Ing. Química");
        nicole.setFirstName("Nicole Alejandra");
        nicole.setLastName("Martin Ramos");
        nicole.setRut("18.863.588-1");
        return nicole;
    }

    @Test
    public void whenFindByRutThenReturnStudent() {
        entityManager.persist(this.studentMockUp());
        entityManager.flush();
        Student found = studentRepository.findByRut(this.studentMockUp().getRut());
        Assertions.assertThat(found.getRut()).isEqualTo(this.studentMockUp().getRut());
    }

    @Test
    public void givenBirthThenReturnAge() {
        entityManager.persist(this.studentMockUp());
        entityManager.flush();
        Student found = studentRepository.findByRut(this.studentMockUp().getRut());
        Assertions.assertThat(found.getAge()).isEqualTo(24);
    }

    @Test
    public void whenFindByIdThenReturnStudent() {
        Object id = entityManager.persistAndGetId(this.studentMockUp());
        entityManager.flush();
        Student found = studentRepository.findByRut(this.studentMockUp().getRut());
        Assertions.assertThat(found.getId()).isEqualTo(id);
    }
}
