package com.management.MonaLibrary.repository;

import com.management.MonaLibrary.Entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrarianRepository extends JpaRepository<Librarian,Long> {
    public Librarian findByName(String librarianName);

}
