package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends
        //Clase y Tipo del ID de la clase
        JpaRepository<Student, Long> {
    //Busca un estudiante por email (?1 = primer argumento)
    @Query("select s from Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
