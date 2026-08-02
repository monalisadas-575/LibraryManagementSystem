package com.management.MonaLibrary.repository;

import com.management.MonaLibrary.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
    public Student findByRollNumber(String rollNumber);
}
