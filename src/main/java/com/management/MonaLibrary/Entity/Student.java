package com.management.MonaLibrary.Entity;

import com.management.MonaLibrary.util.IdGenerator;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private int age;
    private String rollNumber;
    @ManyToMany
    @JoinTable(name = "Student_book",
    joinColumns = @JoinColumn(name = "Student_Id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "book_name",referencedColumnName = "title"))
    private List<Book> books;
}
