package mingeso.backend.rest.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Student")
public class Student{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @Column(name="rut")
    private String rut;
    
    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "birth")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birth;
    
    @Column(name="career")
    private String career;

    public void setFromStudent( Student newStudent ){
        this.rut = newStudent.getRut();
        this.firstName = newStudent.getFirstName();
        this.lastName = newStudent.getLastName();
        this.birth = newStudent.getBirth();
        this.career = newStudent.getCareer();
    }
}
