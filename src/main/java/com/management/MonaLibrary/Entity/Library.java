package com.management.MonaLibrary.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String city;
    private String libraryName;
    @OneToMany(mappedBy = "library")
    private List<Book> books;

    @OneToOne(orphanRemoval = true,cascade = {CascadeType.ALL})
    @JoinColumn(name = "Library_librarian")
    private Librarian librarian;
}
