package com.management.MonaLibrary.Entity;

import com.management.MonaLibrary.myEnum.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String title;
    private String author;
    private int price;
    @Enumerated(EnumType.STRING)
    private Status bookStatus;
    @ManyToOne
    @JoinColumn(name = "library_book",referencedColumnName = "id")
    private Library library;
    @ManyToMany(mappedBy = "books")
    private List<Student> student;

}
