package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
        // return List.of(new Student(1L,"Mateo", LocalDate.of(1991,4,14),"mateo@email.com",30));
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        System.out.println(student);
        Optional<Student> studentByEmail = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }else {
            studentRepository.save(student);
        }
    }

    public void deleteStudent(Long studentId) {

        boolean existsById = studentRepository.existsById(studentId);
        if(!existsById){
            throw new IllegalStateException("Student with id " + studentId + " does not exist!");
        }else{
            studentRepository.deleteById(studentId);
        }
    }
    @Transactional
    public void updateStudent(Student student) {
        boolean existsById = studentRepository.existsById(student.getId());
        if(!existsById){
            throw new IllegalStateException("Student with id " + student.getId() + " does not exist!");
        }else{
            studentRepository.save(student);
        }
    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(
                        () -> new IllegalStateException("Student with id " + studentId + " does not exist!")
                );
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentByEmail = studentRepository
                    .findStudentByEmail(student.getEmail());
            if (studentByEmail.isPresent()){
                throw new IllegalStateException("email taken");
            }else {
                student.setEmail(email);
            }
        }
    }
}
