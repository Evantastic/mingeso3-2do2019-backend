package mingeso.backend.rest.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Student")
public class Student{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name= "id", unique = true, nullable = false)
    private int id;
    
    @Column(name="rut")
    private String rut;
    
    @Column(name="name")
    private String name;
    
    @Column(name="age")
    private short age;
    
    @Column(name="career")
    private String career;

    public void setFromStudent( Student newStudent ){
        this.rut = newStudent.getRut();
        this.name = newStudent.getName();
        this.age = newStudent.getAge();
        this.career = newStudent.getCareer();
    }
}
