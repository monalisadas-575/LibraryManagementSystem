package com.management.MonaLibrary.repository;

import com.management.MonaLibrary.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {
    public Book findByTitle(String title);
    //public List<Book> findByTitle(List<String> titles);
}
