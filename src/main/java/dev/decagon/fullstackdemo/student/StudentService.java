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
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if (studentByEmail.isPresent()){
            throw new BadRequestException("Email Already Taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.findById(studentId)
                .ifPresentOrElse(studentRepository::delete,
                        () -> {throw new StudentNotFoundException("Student With Id: " + studentId +" Not Found");});
    }
}
