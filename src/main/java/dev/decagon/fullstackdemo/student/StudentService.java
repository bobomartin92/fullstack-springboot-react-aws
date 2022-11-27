package dev.decagon.fullstackdemo.student;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        boolean existsStudentByEmail = studentRepository.existsStudentByEmail(student.getEmail());

        if (existsStudentByEmail){
            throw new BadRequestException("Email Already Taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if(!studentRepository.existsById(studentId)){
            throw new StudentNotFoundException("Student With Id: " + studentId +" Not Found");
        }

        studentRepository.deleteById(studentId);
    }
}
