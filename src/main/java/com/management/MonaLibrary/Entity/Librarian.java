package com.management.MonaLibrary.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String email;
    @OneToOne(mappedBy = "librarian")
    private Library library;

}
