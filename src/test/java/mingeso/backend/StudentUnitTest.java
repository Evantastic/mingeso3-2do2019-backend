package mingeso.backend;

import mingeso.backend.rest.student.Student;
import mingeso.backend.rest.student.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Test
    public void whenFindByRut_thenReturnStudent() {
        //given
        Student nicole = new Student();
        nicole.setBirth(LocalDate.of(1995,06,14));
        nicole.setCareer("Ing. Química");
        nicole.setFirstName("Nicole Alejandra");
        nicole.setLastName("Martin Ramos");
        nicole.setRut("18.863.588-1");
        entityManager.persist(nicole);
        entityManager.flush();
        //when
        Student found = studentRepository.findByRut(nicole.getRut());
        //then
        Assertions.assertThat(found.getRut()).isEqualTo(nicole.getRut());
    }

    @Test
    public void givenBirth_thenReturnAge() {
        //given
        Student nicole = new Student();
        nicole.setBirth(LocalDate.of(1995,06,14));
        nicole.setCareer("Ing. Química");
        nicole.setFirstName("Nicole Alejandra");
        nicole.setLastName("Martin Ramos");
        nicole.setRut("18.863.588-1");
        entityManager.persist(nicole);
        entityManager.flush();
        //when
        Student found = studentRepository.findByRut(nicole.getRut());
        //then
        Assertions.assertThat(found.getAge()).isEqualTo(24);
    }

    @Test
    public void whenFindById_thenReturnStudent() {
        //given
        Student nicole = new Student();
        nicole.setBirth(LocalDate.of(1995,06,14));
        nicole.setCareer("Ing. Química");
        nicole.setFirstName("Nicole Alejandra");
        nicole.setLastName("Martin Ramos");
        nicole.setRut("18.863.588-1");
        Object id = entityManager.persistAndGetId(nicole);
        entityManager.flush();
        //when
        Student found = studentRepository.findByRut(nicole.getRut());
        //then
        Assertions.assertThat(found.getId()).isEqualTo(id);
    }
}
